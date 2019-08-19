package com.gxz.enums;

/**
 * 订单状态
 * @author CodeLab
 *
 */
public enum OrderStatus {
 
	PRE_ORDER(0,"预订单"),
	EXPIRE_ORDER(1,"过期订单"),
	ORDER_COMPLATE(2,"订单完成"),
	CANCLE_ORDER(3,"订单取消");
	
	private byte code;
	private String desc;
	
	OrderStatus(int code,String desc){
		this.code  = (byte) code ;
		this.desc = desc ;
	}
	
	public byte getCode() {
		return code;
	}
	public void setCode(byte code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
