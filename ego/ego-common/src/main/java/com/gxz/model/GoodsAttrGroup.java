package com.gxz.model;

import java.io.Serializable;
import java.util.List;

public class GoodsAttrGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer catId;

	private String group;
	private List<?> params;

	public GoodsAttrGroup() {
	}

	public GoodsAttrGroup(Integer catId, String group, List<?> params) {
		super();
		this.catId = catId;
		this.group = group;
		this.params = params;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<?> getParams() {
		return params;
	}

	public void setParams(List<?> params) {
		this.params = params;
	}

}
