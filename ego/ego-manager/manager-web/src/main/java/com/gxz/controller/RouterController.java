package com.gxz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

	/*********拦截器   需要请求跳转到主页面*********/
	@RequestMapping("/toIndex")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/goods/{page}")
	public String routerGoods(@PathVariable("page") String page) {
		return "goods/goods-"+page;
	}
	
	@RequestMapping("/content/{page}")
	public String routerController(@PathVariable("page") String page) {
		return "content/content-"+page;
	}
	
	@RequestMapping("/goods/param-add")
	public String routeGoodsPatamToAdd() {
		return "goods/goods-attribute-add";
	}
	
}
