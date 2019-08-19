package com.gxz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gxz.domain.Content;
import com.gxz.model.TreeNode;
import com.gxz.service.ContentService;
import com.gxz.service.GoodsCatService;

@RestController
public class HomeController {
	
	@Reference
	private GoodsCatService goodsCatService;
	@Reference
	private ContentService contentService;
	
	//前台轮播图的显示
	@Value("${Home.categoryId}")
	private Integer categoryId;
	@Value("${Home.pageSize}")
	private Integer pageSize;
	
	@GetMapping("/goods/cat/getAllMenu")
	public List<TreeNode> loadAllMenu() {
		List<TreeNode> trees = goodsCatService.loadAllTree();
		if(null == trees) return null;
		return trees;
	}
	
	@GetMapping("/home/content/loadAdvice")
	public Object loadAdvice() {
		List<Content> data = contentService.loadAdviceByCatId(categoryId,pageSize);
		if(null == data) return null;
		return data;
	}
}
