package com.gxz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EasyUITree implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String text;
	private String state;
	private Boolean checked;
	private List<EasyUITree> children;
	
	public static List<EasyUITree> treeChangeEasyUITree(List<TreeNode> treeNodes){
		List<EasyUITree> easyuiTrees = new ArrayList<>();
		
		treeNodes.forEach((treeNode)->{
			EasyUITree easyUITree = new EasyUITree();
			easyUITree.setId(treeNode.getId());
			easyUITree.setText(treeNode.getName());
			easyUITree.setState(treeNode.getIsOpen()==true?"open":"closed");
			easyuiTrees.add(easyUITree);
		});
		
		return easyuiTrees;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<EasyUITree> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUITree> children) {
		this.children = children;
	}

}
