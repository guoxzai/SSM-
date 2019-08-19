package com.gxz.model;

import java.io.Serializable;
import java.util.List;

/**
 * 高层封装的树模型
 * 该树能被转化为多种形式：eg:EasyUiTree Ztree 文件目录
 * @author User
 *
 */
public class TreeNode implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String name;
	private List<TreeNode> childrens;
	private String attr;
	private Boolean isOpen;
	
	//前台获取的值
	private Integer parentId;
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeNode> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<TreeNode> childrens) {
		this.childrens = childrens;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
