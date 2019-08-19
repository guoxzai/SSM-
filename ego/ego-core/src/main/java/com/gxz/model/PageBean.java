package com.gxz.model;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	// 当前页
	private Integer page;
	// 每页显示的条数
	private Integer size;
	// 总条数
	private Long total;
	// 本页数据
	private List<T> data;

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(Integer page, Integer size, Long total, List<T> data) {
		super();
		this.page = page;
		this.size = size;
		this.total = total;
		this.data = data;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
