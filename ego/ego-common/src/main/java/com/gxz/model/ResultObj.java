package com.gxz.model;

import java.io.Serializable;

public class ResultObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private Object msg;

	public ResultObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultObj(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

}
