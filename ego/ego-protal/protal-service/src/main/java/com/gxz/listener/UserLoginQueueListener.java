package com.gxz.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.gxz.domain.Admin;
import com.gxz.domain.Cart;
import com.gxz.domain.CartExample;
import com.gxz.domain.CartExample.Criteria;
import com.gxz.mapper.CartMapper;
import com.gxz.redis.RedisClient;

@Component
public class UserLoginQueueListener implements MessageListener {

	@Autowired
	private RedisClient redis;
	@Autowired
	private CartMapper cartMapper;
	
	//concurrency R 设置线程个数提高消费速度  解决生产过剩消息的推积
	@JmsListener(containerFactory="containerFactory",destination="user.login.queue",concurrency="3-5")
	@Override
	public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage)message;
		String token = null;
		String label = null;
		try {
			String text = textMessage.getText();
			token = text.split("#")[0];
			label = text.split("#")[1];
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		Admin admin = getUserByToken(token);

		// 数据转移
		if (admin != null) {
			// 拷贝redis中的数据 到数据库中 同步用户
			transDataFromRedisToDB(admin.getId().intValue(), label);
		}
	}

	private void transDataFromRedisToDB(Integer userId, String label) {
		String goodsInfoLabel = label + "GOODSINFO";

		Map<String, String> goodsInfo = redis.hgetAll(goodsInfoLabel);
		if (null == goodsInfo || goodsInfo.size() == 0)
			return;

		Set<String> goodsIdsStr = goodsInfo.keySet();
		List<Integer> goodsId = new ArrayList<>();
		goodsIdsStr.forEach((id) -> {
			goodsId.add(Integer.valueOf(id));
		});

		CartExample example = new CartExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andGoodsIdIn(goodsId);
		List<Cart> carts = cartMapper.selectByExample(example);
		// 交集的计算
		for (Cart cart : carts) {
			String goodsRedisNumber = goodsInfo.get(cart.getId() + "");
			cart.setNum(cart.getNum() + Integer.valueOf(goodsRedisNumber));
			cartMapper.updateByPrimaryKeySelective(cart);
			goodsId.remove(cart.getId());
		}

		// 差集的计算
		goodsId.forEach((gId) -> {
			String goodsRedisNumber = goodsInfo.get(gId + "");

			Cart cart = new Cart();
			cart.setGoodsId(gId);
			cart.setUserId(userId);
			cart.setNum(Integer.valueOf(goodsRedisNumber));
			cartMapper.insertSelective(cart);
		});

		String goodsListKey = label + "GOODSLIST";
		String goodsCountKey = label + "GOODSCOUNT";
		redis.deleteValue(goodsInfoLabel, goodsListKey, goodsCountKey);
	}
	
	private Admin getUserByToken(String token) {
		if (null == token || "".equals(token))
			return null;
		if (!redis.isExist(token))
			return null;

		String userJson = redis.get(token);
		Admin admin = JSON.parseObject(userJson, Admin.class);

		return admin;
	}
}
