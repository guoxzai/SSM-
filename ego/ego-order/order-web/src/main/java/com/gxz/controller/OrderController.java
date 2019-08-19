package com.gxz.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.gxz.domain.Admin;
import com.gxz.domain.Order;
import com.gxz.enums.OrderStatus;
import com.gxz.model.OrderVo;
import com.gxz.model.PageBean;
import com.gxz.model.ResultObj;
import com.gxz.model.SearchGoodsVo;
import com.gxz.redis.RedisClient;
import com.gxz.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private RedisClient redisClient;
	
	@Reference
	private OrderService orderService;
	
	/**
	 * 使用redis解决超库
	 * 
	 * 前台Ajax异步回调
	 * @param orderVo
	 * @return
	 */
	@RequestMapping("/order/submit")
	@ResponseBody
	public String submitOrder(String callBackFunc,OrderVo orderVo) {
		ResultObj obj = null;
		
		List<SearchGoodsVo> goodsVoList = orderVo.getGoodsVoList();
		//将库存信息存放到redis中
		Map<String,String> orderGoodsStoreNum = new HashMap<>();
		for (SearchGoodsVo searchGoodsVo : goodsVoList) {
			try {
				String goodsName = new String(searchGoodsVo.getGoodsNameHl().getBytes("ISO-8859-1"),"UTF-8");
				searchGoodsVo.setGoodsNameHl(goodsName);
				String id = searchGoodsVo.getId();
				Integer goodsCommentCount = searchGoodsVo.getGoodsCommentCount();
				
				String redisValue = redisClient.get("store:"+id);
				if(null == redisValue) {
					obj = new ResultObj(400, "商品"+goodsName+"在订单中不存在");
					StringBuilder sb = new StringBuilder("callBackFunc(");
					sb.append(JSON.toJSONString(obj));
					sb.append(")");
					return sb.toString();
					
				}else {
					Integer newOrderCount = Integer.valueOf(redisValue)-goodsCommentCount;
					if(newOrderCount < 0) {
						obj = new ResultObj(400, "商品"+goodsName+"库存不足剩余:"+Integer.valueOf(redisValue));
						StringBuilder sb = new StringBuilder("callBackFunc(");
						sb.append(JSON.toJSONString(obj));
						sb.append(")");
						return sb.toString();
					}
					
					orderGoodsStoreNum.put(id, newOrderCount+"");
				}
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}
		
		orderGoodsStoreNum.forEach((goodsId,goodsNum)->{
			redisClient.set("store:"+goodsId, goodsNum);
		});
		
		Admin admin = new Admin();
		admin.setId((short)1);
		orderVo.setAdmin(admin);
		jmsTemplate.convertAndSend("order.pre.queue", orderVo);
		
		StringBuilder sb = new StringBuilder("callBackFunc(");
		sb.append(JSON.toJSONString(obj = new ResultObj(200,"订单提交完成")));
		sb.append(")");

		return sb.toString();
	}
	
	/**
	 * 查询用户未完成的订单
	 */
	@RequestMapping("/order/query")
	public Map<String,Object> queryOrder(Integer status,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="1")Integer size) {
		Map<String,Object> map= new HashMap<String,Object>();
		Admin admin = new Admin();
		admin.setId((short)1);
		OrderStatus orderStatus = null;
		if(status == 0) {
			orderStatus = orderStatus.PRE_ORDER;
		}else if(status == 1) {
			orderStatus = orderStatus.EXPIRE_ORDER;
		}else if(status == 2){
			orderStatus = orderStatus.ORDER_COMPLATE;
		}else {
			orderStatus = orderStatus.CANCLE_ORDER;
		}
		
		PageBean<Order> pageBean = orderService.queryOrder(admin.getId().intValue(),orderStatus,page,size);
		
		map.put("data", pageBean.getData());
		return map;
	}
	
	/**
	 * 不能使用docker中的jms
	 * 要使用自己设置的jms
	 * 
	 * 测试MQ
	 * @return
	 */
	@RequestMapping("/test")
	public String testSendSchedulerSupport() {
		jmsTemplate.convertAndSend("pre.order","heihei",new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				// 给该消息设置验证
				message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 60*1000);
				// 支持cron 表达式 ,优先支持cron
//				message.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "");
				// 该消息会重复投递
//				message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 1);
				return message;
			}
		});
		
		return "ok";
	}

}
