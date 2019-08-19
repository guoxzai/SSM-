package com.gxz.model;

import java.io.Serializable;

/*url:"${ctx}/search/doSearch",
data:{"currentPage":currentPage,"pageSize":pageSize,"keywords":keywords,"sort":sort}*/


/**
 * 订单操作前台JSON格式   与购物车中的JSON格式一样
 * 实体类提升
 * @author User
 *
 */
public class SearchGoodsVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/******* 封装Json ********/
	private String id;

	private String goodsImg;
	// 高亮显示
	private String goodsNameHl;

	private String goodsPrice;

	/****** 接受前台的值 ******/
	private Integer currentPage;
	private Integer pageSize;
	private String keywords;
	// 排序
	private String sort;
	// 前台价格区间判断 模糊查询
	private Integer min;
	private Integer max;

	/***** 购物车 **********/
	// 回显商品的数量准备的字段
	private Integer goodsCommentCount;


	public Integer getGoodsCommentCount() {
		return goodsCommentCount;
	}

	public void setGoodsCommentCount(Integer goodsCommentCount) {
		this.goodsCommentCount = goodsCommentCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getGoodsNameHl() {
		return goodsNameHl;
	}

	public void setGoodsNameHl(String goodsNameHl) {
		this.goodsNameHl = goodsNameHl;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

}
