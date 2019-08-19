package com.gxz.model;

import java.io.Serializable;

import com.gxz.domain.Goods;

public class GoodsVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Goods goods;
	private String itemParams;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getItemParams() {
		return itemParams;
	}

	public void setItemParams(String itemParams) {
		this.itemParams = itemParams;
	}
	
}
