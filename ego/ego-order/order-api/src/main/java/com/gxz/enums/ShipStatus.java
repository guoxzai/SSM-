package com.gxz.enums;

/**
 * 发货状态
 * @author CodeLab
 *
 */
public enum ShipStatus {
 
	NOT_SHIP(0,"未发货"),
	TRANSPORTING(1,"运输中"),
	SIGNED(2,"已签收");
	
	private byte code;
	private String desc;
	
	ShipStatus(int code,String desc){
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
