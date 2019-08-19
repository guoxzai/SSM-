package com.gxz.enums;

/**
 * 支付状态
 * @author CodeLab
 *
 */
public enum PayStatus {
 
	NOT_PAY(0,"未支付"),
	PAY_FAIL(1,"支付失败"),
	PAYED(3,"支付完成");
	private byte code;
	private String desc;
	
	PayStatus(int code,String desc){
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
