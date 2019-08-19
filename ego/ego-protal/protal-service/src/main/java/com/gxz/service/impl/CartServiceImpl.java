package com.gxz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.gxz.domain.Admin;
import com.gxz.domain.Cart;
import com.gxz.domain.CartExample;
import com.gxz.domain.CartExample.Criteria;
import com.gxz.mapper.CartMapper;
import com.gxz.redis.RedisClient;
import com.gxz.service.CartService;

/**
 * 商品 ids在redis存用zset 通过隐藏分排序 goodsItem用hash存 方便存取
 * 
 * @author User
 *
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RedisClient redis;
	@Autowired
	private CartMapper cartMapper;

	/**
	 * 使用Token来判断用户是不是登录 若是 将Redis中的数据拷贝到数据库中 若不是 直接存入缓存中 下次再次查询根据前台设置的cookie label
	 * 从redis取值
	 */
	@Override
	public void addCart(Integer goodsId, Integer goodsNum, String label, String token) {
		// 使用Token获取用户对象
		Admin admin = getUserByToken(token);

		// 数据转移
		if (admin != null) {
			/************ 先加购物车后登陆 *****************/
			//MQ实时性集成
			// 拷贝redis中的数据 到数据库中 同步用户
			//transDataFromRedisToDB(admin.getId().intValue(), label);
			
			// 后面的数据添加直接添加到数据库中
			addCartToDB(admin.getId().intValue(), goodsId, goodsNum);

		} else {
			// 直接存入Redis中
			addCartToRedis(goodsId, goodsNum, label);
		}

	}

	/**
	 * 获取总条数
	 * 
	 * 可以不需要数据转移
	 * 用户存在从数据库中查
	 * 不存在从redis里面查
	 */
	@Override
	public long getTotal(String label, String token) {
		Admin admin = getUserByToken(token);

		if (null != admin) {
			//MQ实时性集成
			//transDataFromRedisToDB(admin.getId().intValue(), label);
			return getTotalFromDB(admin.getId().intValue());
		} else {
			return getTotalFromRedis(label);
		}

	}

	/**
	 *  显示购物车信息
	 */
	@Override
	public Map<String, String> getGoodsInfo(String label, String token) {
		Admin admin = getUserByToken(token);
		
		String goodsInfoLabel = label+"GOODSINFO";
		Map<String,String> goodsInfo = new HashMap<>();
		
		//用户没有登录
		if(null == admin) {
			Map<String, String> hgetAll = redis.hgetAll(goodsInfoLabel);
			return hgetAll;
		}
		
		CartExample example = new CartExample();
		example.createCriteria().andUserIdEqualTo(admin.getId().intValue());
		List<Cart> carts = cartMapper.selectByExample(example );
		carts.forEach((cart)->{
			goodsInfo.put(cart.getGoodsId()+"", cart.getNum()+"");
		});
		
		return goodsInfo;
	}

	private void addCartToDB(Integer userId, Integer goodsId, Integer goodsNum) {
		CartExample example = new CartExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andGoodsIdEqualTo(goodsId);
		List<Cart> carts = cartMapper.selectByExample(example);
		// 之前没有该商品 直接添加
		if (carts.size() == 0) {
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setGoodsId(goodsId);
			cart.setNum(goodsNum);
			carts.add(cart);

			cartMapper.insertSelective(cart);
			return;
		}

		// 之前有该商品 在原来基础上添加
		if (carts.size() == 1) {
			Cart cart = carts.get(0);
			cart.setNum(cart.getNum() + goodsNum);

			cartMapper.updateByPrimaryKeySelective(cart);
			return;
		}

		throw new RuntimeException("数据库异常");
	}

	private void addCartToRedis(Integer goodsId, Integer goodsNum, String label) {
		String goodsListKey = label + "GOODSLIST";
		String goodsInfoKey = label + "GOODSINFO";
		String goodsCountKey = label + "GOODSCOUNT";

		double incre = redis.incre(goodsCountKey);
		redis.zadd(goodsListKey, goodsId + "", incre);

		// 统计数量 判断该商品之前添加过
		Integer goodsLastNumber = 0;
		if (redis.isHashExist(goodsInfoKey, goodsId + "")) {
			goodsLastNumber = Integer.parseInt(redis.hget(goodsInfoKey, goodsId + ""));
		}
		redis.hset(goodsInfoKey, goodsId + "", (goodsLastNumber + goodsNum) + "");
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

	private Long getTotalFromDB(Integer userId) {
		return cartMapper.getGoodsNumberTotal(userId)==null?0L:cartMapper.getGoodsNumberTotal(userId);
	}

	private long getTotalFromRedis(String label) {
		long total = 0L;
		Map<String, String> goodsInfo = redis.hgetAll(label + "GOODSINFO");
		if (null != goodsInfo) {
			Set<String> set = goodsInfo.keySet();
			for (String id : set) {
				total += Long.valueOf(goodsInfo.get(id));
			}
		}

		return total;
	}

	/*******************订单操作 清除购物车*******************/
	/**
	 * 清空购物车
	 */
	@Transactional
	@Override
	public long clearCartAll(Map<Integer,Integer> goodsInfo,Integer userId) {
		if(null == goodsInfo || goodsInfo.size()==0 ) throw new RuntimeException("goodsId不能为空");
		long total = 0L;
		Set<Integer> goodsIds = goodsInfo.keySet();
		for (Integer goodsId : goodsIds) {
			
			CartExample example = new CartExample();
			example.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
			List<Cart> carts = cartMapper.selectByExample(example );
			if(null != carts && carts.size()>0) {
				Cart cart = carts.get(0);
				Integer newCartNum = cart.getNum() - goodsInfo.get(goodsId);
				
				if(newCartNum > 0) {
					cart.setNum(newCartNum);
					total += cartMapper.updateByPrimaryKey(cart);
				}else {
					total += cartMapper.deleteByPrimaryKey(cart.getId());
				}
				
			}
			
		}
		
		return total;
	}
	
	/**
	 *  出现异常  事务的补偿   
	 */
	@Transactional
	@Override
	public long addAllCart(Map<Integer,Integer> goodsInfo, Integer userId) {
		long total = 0L;
		if(null == goodsInfo || goodsInfo.size()==0 ) throw new RuntimeException("goodsId不能为空");
		Set<Integer> goodsIds = goodsInfo.keySet();
		for (Integer goodsId : goodsIds) {
			
			CartExample example = new CartExample();
			example.createCriteria().andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
			List<Cart> carts = cartMapper.selectByExample(example );
			if(null != carts && carts.size()>0) {
				Cart cart = carts.get(0);
				cart.setNum(cart.getNum()+goodsInfo.get(goodsId));
				total += cartMapper.updateByPrimaryKey(cart);
				
			}else {
				Cart cart = new Cart();
				cart.setGoodsId(goodsId);
				cart.setUserId(userId);
				cart.setNum(goodsInfo.get(goodsId));
				
				total += cartMapper.insert(cart);
			}
			
		}
		
		return total;
	}
	
	/*private void transDataFromRedisToDB(Integer userId, String label) {
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
}*/
	
}
