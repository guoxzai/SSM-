package com.gxz.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class Pay implements Serializable {

	private static final long serialVersionUID = 1L;

	// 所支持的支付方式
	public static final Integer SCAN_CODE_PAY = 1;
	public static final Integer COMPUTER_PAY = 2;

	// 支付类型
	@JSONField(serialize = false)
	private Integer payType;

	/****** 支付宝网页付款所需要的数据 *******/
	// 商品订单号
	@JSONField(name = "out_trade_no")
	private String outTradeNo;

	// 订单名称
	private String subject;

	// 付款金额 用分计
	@JSONField(name = "total_amount")
	private String totalAmount;

	// 商品描述
	private String body;

	@JSONField(name = "product_code")
	private String productCode;

	/****** 支付宝二维码付款所需要的数据 *******/
	// (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
	@JSONField(serialize = false)
	private String storeId;
	// 支付超时，定义为120分钟
	@JSONField(name = "timeout_express")
	private String timeoutExpress;

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getTimeoutExpress() {
		return timeoutExpress;
	}

	public void setTimeoutExpress(String timeoutExpress) {
		this.timeoutExpress = timeoutExpress;
	}

}
