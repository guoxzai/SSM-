package com.gxz.model;

import java.io.Serializable;
import java.util.List;

/**
 * 模糊查询商品 按前台需求拼JSON
 * 
 * @author User
 *
 */
public class SearchGoodsResult implements Serializable {

	private static final long serialVersionUID = 1L;

	// 当前页
	private Integer page;
	// 总条数
	private Long total;

	// 递归搜索SearchGoodsVo也需要实现Serializable接口
	private List<SearchGoodsVo> results;

	// 断路器的判断
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<SearchGoodsVo> getResults() {
		return results;
	}

	public void setResults(List<SearchGoodsVo> results) {
		this.results = results;
	}

}
