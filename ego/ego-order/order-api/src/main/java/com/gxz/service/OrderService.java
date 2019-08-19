package com.gxz.service;

import com.gxz.domain.Order;
import com.gxz.enums.OrderStatus;
import com.gxz.model.OrderVo;
import com.gxz.model.PageBean;

import java.util.Map;

public interface OrderService {

	PageBean<Order> queryOrder(Integer shortValue, OrderStatus orderStatus, Integer page, Integer size);

	/**
	 * 通过orderSn 查询订单
	 * @param orderSn
	 * @return
	 */
	OrderVo queryOrderByOrderSn(String orderSn);

	/**
	 * 根据订单号查询订单
	 * @param orderSn
	 * @return
	 */
	Order query(String orderSn);

	/**
	 * 支付宝通知我们系统，用户付钱成功了
	 * @param params
	 * @return
	 */
	int updateOrderStatus(Map<String,String> params);
}
