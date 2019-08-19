package com.gxz.service;

import java.util.Map;

public interface CartService {

	//Label  浏览器的标识便于在    redis中存储
	void addCart(Integer goodsId,Integer goodsNum,String label,String token);
	
	long getTotal(String label,String token);

	Map<String, String> getGoodsInfo(String label, String token);
	
	//清空购物车
	long clearCartAll(Map<Integer,Integer> goodsInfo,Integer userId);
	long addAllCart(Map<Integer,Integer> goodsInfo, Integer userId); 
}
