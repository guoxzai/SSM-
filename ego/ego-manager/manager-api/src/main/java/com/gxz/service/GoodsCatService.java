package com.gxz.service;

import java.util.List;

import com.gxz.model.TreeNode;

public interface GoodsCatService{
	List<TreeNode> loadTreeByPid(Integer pid);
	List<TreeNode> loadAllTree();
	
	Integer add(TreeNode treeNode);
	Integer update(TreeNode treeNode);
	Integer delete(Integer id);
	
	List<Integer> findSubIdsByParent(Integer catId);
	
}
