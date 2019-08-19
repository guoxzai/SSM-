package com.gxz.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gxz.domain.Order;
import com.gxz.domain.OrderAction;
import com.gxz.domain.OrderExample;
import com.gxz.domain.OrderExample.Criteria;
import com.gxz.domain.OrderGoods;
import com.gxz.enums.OrderStatus;
import com.gxz.enums.PayStatus;
import com.gxz.enums.ShipStatus;
import com.gxz.mapper.OrderActionMapper;
import com.gxz.mapper.OrderGoodsMapper;
import com.gxz.mapper.OrderMapper;
import com.gxz.model.OrderVo;
import com.gxz.model.PageBean;
import com.gxz.model.SearchGoodsVo;
import com.gxz.redis.RedisClient;
import com.gxz.service.CartService;
import com.gxz.service.OrderService;
import com.gxz.utils.AuthCodeUtil;
import com.gxz.utils.DateUtil;

@Service(retries=1)
public class OrderServiceImpl implements OrderService,MessageListener {

	@Reference
	private CartService cartService;
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	@Autowired
	private OrderActionMapper orderActionMapper;
	
	@Autowired
	private RedisClient redis;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@JmsListener(containerFactory="containerFactory",destination="order.pre.queue",concurrency="3-5")
	@Override
	public void onMessage(Message message) {
		ObjectMessage objMessage = (ObjectMessage) message;
		
		OrderVo orderVo = null;
		try {
			orderVo = (OrderVo) objMessage.getObject();
			//生成订单
			createPreOrder(orderVo);
			message.acknowledge();
			
		} catch (JMSException e) {
			e.printStackTrace();
			
			int i=3;
			while(i>0) {
				try {
					//调用方法重试机制
					createPreOrder(orderVo);
					break;
				} catch (Exception e1) {
					i--;
					e1.printStackTrace();
				}
				
				try {
					message.acknowledge();
				} catch (JMSException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		
	}

	/**
	 * 订单生成   
	 * 1 在数据库中添加相应数据
	 * 2 分布式事务   合并式事务   事务补偿
	 * 	 数据库中库存 --      事务补偿
	 * 3 订单延迟
	 * @param orderVo
	 */
	@Transactional
	void createPreOrder(OrderVo orderVo) {
		Map<Integer,Integer> goodsInfo = new HashMap<>();
		
		Integer userId = orderVo.getAdmin().getId().intValue();
		try {
			List<SearchGoodsVo> goodsVoList = orderVo.getGoodsVoList();
			for (SearchGoodsVo searchGoodsVo : goodsVoList) {
				goodsInfo.put(Integer.parseInt(searchGoodsVo.getId()), searchGoodsVo.getGoodsCommentCount());
			}
			//清空购物车
			cartService.clearCartAll(goodsInfo, userId);
			
			//雪花算法  生成随机数
			String orderSn = getOrdersn();
			//订单的添加   主键Id  不是订单Id
			Integer orderId = handlerOrder(userId,orderSn);
			if(null != orderId) {
				//订单中的商品添加   数据库中的库存	
				handelOrderGoods(orderId,goodsVoList);
				//添加预订单   记录订单的状态和信息
				handelOrderAction(orderId,userId);
			}
			
			/**
			 * 使用延迟队列实现定时任务
			 */
			//将预订单放在延迟队列中   若多长时间没有消费队列 就取消下订单 就改回原来的状态
			jmsTemplate.convertAndSend("order.temp.queue", orderId + "", new MessagePostProcessor() {
				
				// 添加延迟特效
				@Override
				public Message postProcessMessage(Message message) throws JMSException {
					message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 60*1000);//1分钟
					return message;
				}
			});
			
		}catch (Exception e) {
			e.printStackTrace();
			// 有异常，执行回归 ，使用补偿的方案回归
			cartService.addAllCart(goodsInfo, userId);
		}

		
	}
	
	private void handelOrderAction(Integer orderId, Integer userId) {
		OrderAction orderAction = new OrderAction();
		orderAction.setActionUser(userId);
		orderAction.setActionNote("增加一个预订单");
		orderAction.setOrderId(orderId);
		orderAction.setOrderStatus(OrderStatus.PRE_ORDER.getCode());
		orderAction.setPayStatus(PayStatus.NOT_PAY.getCode());
		orderAction.setShippingStatus(ShipStatus.NOT_SHIP.getCode());
		//orderAction.setLogTime(1); // 和order 里面的addTime 一样
		orderActionMapper.insert(orderAction);
	}

	private void handelOrderGoods(Integer orderId, List<SearchGoodsVo> goodsVoList) {
		for (SearchGoodsVo searchGoodsVo : goodsVoList) {
			OrderGoods orderGoods = new OrderGoods();
			orderGoods.setOrderId(orderId);
			orderGoods.setGoodsId(Integer.valueOf(searchGoodsVo.getId()));
			orderGoods.setGoodsName(searchGoodsVo.getGoodsNameHl());
			orderGoods.setGoodsNum(searchGoodsVo.getGoodsCommentCount().shortValue());
			orderGoods.setGoodsPrice(new BigDecimal(searchGoodsVo.getGoodsPrice()));
		    // 数据库的库存--
			orderGoodsMapper.insert(orderGoods);
		}
	}

	private Integer handlerOrder(Integer userId, String orderSn) {
		Order order = new Order();
		order.setOrderSn(orderSn);
		order.setUserId(userId);
		
		order.setOrderStatus(OrderStatus.PRE_ORDER.getCode());
		order.setPayStatus(PayStatus.NOT_PAY.getCode());
		order.setShippingStatus(ShipStatus.NOT_SHIP.getCode());
		//order.setAddTime();
		
		orderMapper.insert(order);
		return order.getId();
	}

//	https://blog.csdn.net/u012488504/article/details/82194495
	 // 生成订单编号 时间 可以生成随机数 自增，使用雪花算法生成（分布式里面id的生成）来自
//	leaf 美团id 生成id https://tech.meituan.com/2017/04/21/mt-leaf.html
	// 时间+随机+自增 20 2019 0408 1430 4个 0001   99990001 0002 0003
	private String getOrdersn() {
		String today = DateUtil.getDate("yyyy-MM-dd");
		Integer num= (int) redis.incre("EGO-ORDER"+today);  //0
		String date  = DateUtil.getDate("yyyyMMddHHmm");
		// 生成一个订单号
		String orderSn = date+AuthCodeUtil.generatorCode(4)+AuthCodeUtil.genIncre(num+"", 4);
		return orderSn;
	}

	/*****************查询订单*********************/
	
	@Override
	public PageBean<Order> queryOrder(Integer userId, OrderStatus orderStatus, Integer page, Integer size) {
		if(null == userId) throw new RuntimeException("用户不能为空");
		
		OrderExample example = new OrderExample();
		example.setOrderByClause("add_time desc");
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		if(null != orderStatus) {
			criteria.andOrderStatusEqualTo(orderStatus.getCode());
		}
		
		Page<Order> pageHelper = PageHelper.startPage(page, size);
		List<Order> orders = orderMapper.selectByExample(example );
		
		
		return new PageBean<>(page,size,pageHelper.getTotal(),orders);
	}

	/**
	 * 通过orderSn 查询订单
	 *
	 * @param orderSn
	 * @return
	 */
	@Override
	public OrderVo queryOrderByOrderSn(String orderSn) {
		return null;
	}

	/**
	 * 根据订单号查询订单
	 *
	 * @param orderSn
	 * @return
	 */
	@Override
	public Order query(String orderSn) {
		return null;
	}

	/**
	 * 支付宝通知我们系统，用户付钱成功了
	 *
	 * @param params
	 * @return
	 */
	@Override
	public int updateOrderStatus(Map<String, String> params) {
		return 0;
	}

}
