package com.gxz.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gxz.model.SearchGoodsResult;
import com.gxz.model.SearchGoodsVo;
import com.gxz.service.SearchGoodsService;

/**
 * solr导入数据  在manager-service的层中
 * @author User
 *
 */

@Controller
public class SearchController {
	
	@Reference
	private SearchGoodsService SearchGoodsService;
	
	@RequestMapping("/search/goSearch")
	public String toSeachIndex(String keywords,Model model) {
		try {
			keywords = new String(keywords.getBytes("ISO-8859-1"),"utf-8");
			model.addAttribute("keywords", keywords);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "search";
	}
	
	
	/**
	 * 类别查商品合并搜索
	 * @param searchGoodsVo
	 * @return
	 */
	@RequestMapping("/search/doSearch")
	@ResponseBody
	public SearchGoodsResult doSearchGoods(SearchGoodsVo searchGoodsVo) {
		if(null == searchGoodsVo.getCurrentPage() || null == searchGoodsVo.getPageSize()) {
			searchGoodsVo.setCurrentPage(1);searchGoodsVo.setPageSize(10);
		}
		
		String sort = searchGoodsVo.getSort();
		Integer min = searchGoodsVo.getMin();
		Integer max = searchGoodsVo.getMax();
		
		if(null==sort||"".equals(sort)) {
			return SearchGoodsService.doSearchGoods(searchGoodsVo,false,true,min,max); // 销量从大到小 价格从低到高 
		}
		if("price".equals(sort)) { // 价格排序
			return SearchGoodsService.doSearchGoods(searchGoodsVo,true, false,min,max);
		}
		if("comment".equals(sort)) { // 价格排序
			return SearchGoodsService.doSearchGoods(searchGoodsVo,false,true,min,max);
		}
		
		return null;
	}
	
	//跳转到种类查询
	@RequestMapping("/goods/cat/{catId}")
	public String goQuery(@PathVariable("catId")Integer catId,Model model) {
		model.addAttribute("catId", catId);
		return "search";
	}

	/**
	 * 前台根据种类列表查询商品
	 */
	/*@RequestMapping("/search/doQuery")
	@ResponseBody*/
	public SearchGoodsResult doSearch(Integer catId,String sort,@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="10")Integer rows,Integer min,Integer max) {
		if(null==sort||"".equals(sort)) {
			return SearchGoodsService.doQuery(catId, page, rows,false,true,min,max); // 销量从大到小 价格从低到高 
		}
		if("price".equals(sort)) { // 价格排序
			return SearchGoodsService.doQuery(catId, page, rows, true, false, min,max);
		}
		if("comment".equals(sort)) { // 价格排序
			return SearchGoodsService.doQuery(catId, page, rows, false,true ,min,max);
		}
		return null;
	}
}
