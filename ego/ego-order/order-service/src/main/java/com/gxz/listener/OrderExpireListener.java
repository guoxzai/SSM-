package com.gxz.listener;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.gxz.domain.Order;
import com.gxz.domain.OrderAction;
import com.gxz.domain.OrderActionExample;
import com.gxz.domain.OrderGoods;
import com.gxz.domain.OrderGoodsExample;
import com.gxz.enums.OrderStatus;
import com.gxz.enums.PayStatus;
import com.gxz.mapper.OrderActionMapper;
import com.gxz.mapper.OrderGoodsMapper;
import com.gxz.mapper.OrderMapper;
import com.gxz.redis.RedisClient;

@Component
public class OrderExpireListener implements MessageListener{

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderActionMapper orderActionMapper;
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	
	@Autowired
	private RedisClient redis;
	
	@JmsListener(concurrency="3-5",containerFactory="containerFactory",destination="order.temp.queue")
	@Override
	public void onMessage(Message message) {
		System.out.println("1分钟已经到了，若没有付钱，付钱了做什么");
		TextMessage textMsg = (TextMessage)message ;
		String orderId = null;
		try {
			orderId = textMsg.getText();
			message.acknowledge();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		if(orderId!=null) {
			handlerPreOrder(orderId);
		}
	}

	/**
	 * 30 s后，我该怎么处理订单
	 * 看该订单是否付钱了，若付钱了，不做事情
	 * 没有付钱，将该订单设置订单过期
	 */
	private void handlerPreOrder(String orderId) {
		Order order = orderMapper.selectByPrimaryKey(Integer.valueOf(orderId));
		if(null == order) throw new RuntimeException("订单不存在");
		//若订单未支付
		if(order.getPayStatus() == PayStatus.NOT_PAY.getCode()) {
			order.setOrderStatus(OrderStatus.EXPIRE_ORDER.getCode());
			orderMapper.updateByPrimaryKey(order);
			
			//事务的补偿   商品数量加回去
			OrderGoodsExample example = new OrderGoodsExample();
			example.createCriteria().andOrderIdEqualTo(Integer.valueOf(orderId));
			List<OrderGoods> orderGoodss = orderGoodsMapper.selectByExample(example);
			if(null != orderGoodss && orderGoodss.size()>0) {
				for (OrderGoods orderGoods : orderGoodss) {
					String goodsNum = redis.get("store:"+orderGoods.getGoodsId());
					Integer redisNum = goodsNum == null ? 0:Integer.valueOf(goodsNum);
					//redis          事务补偿
					redis.set("store:"+orderGoods.getGoodsId(), redisNum+orderGoods.getGoodsNum()+"");
					//goods数据库库存      事务补偿
				}
			}
			
			OrderActionExample orderActionExample = new OrderActionExample();
			orderActionExample.createCriteria().andOrderIdEqualTo(Integer.valueOf(orderId));
			List<OrderAction> orderActions = orderActionMapper.selectByExample(orderActionExample );
			if(null != orderActions && orderActions.size()>0) {
				OrderAction orderAction = orderActions.get(0);
				orderAction.setActionNote("订单超时");
				orderAction.setOrderStatus(OrderStatus.EXPIRE_ORDER.getCode());
				orderAction.setPayStatus(PayStatus.NOT_PAY.getCode());
				orderActionMapper.updateByPrimaryKey(orderAction);
			}
		}
	}

}
