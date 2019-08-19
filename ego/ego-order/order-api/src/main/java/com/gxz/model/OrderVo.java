package com.gxz.model;

import java.io.Serializable;
import java.util.List;

import com.gxz.domain.Admin;

public class OrderVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<SearchGoodsVo> goodsVoList;

	private Admin admin;

	private String orderSn;

	private String orderName;

	private String totalMoney;

	private String goodsInfo;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public List<SearchGoodsVo> getGoodsVoList() {
		return goodsVoList;
	}

	public void setGoodsVoList(List<SearchGoodsVo> goodsVoList) {
		this.goodsVoList = goodsVoList;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
