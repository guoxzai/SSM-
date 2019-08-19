package com.gxz.model;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGrid implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long total;
	private List<?> rows;

	public EasyUIDataGrid() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EasyUIDataGrid(Long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
