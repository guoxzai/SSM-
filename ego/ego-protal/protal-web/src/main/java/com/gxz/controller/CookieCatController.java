package com.gxz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.gxz.domain.Goods;
import com.gxz.model.ResultObj;
import com.gxz.model.SearchGoodsResult;
import com.gxz.model.SearchGoodsVo;
import com.gxz.service.GoodsService;
import com.gxz.utils.CookieUtil;

//@Controller
public class CookieCatController {

	@Reference
	private GoodsService goodsService;
	
	@RequestMapping("/cart/list")
	public String toCartListPage() {
		return "cart-list";
	}
	
	/**
	 * 添加购物车
	 */
	@RequestMapping("/cart/add")
	@ResponseBody
	public ResultObj addCart(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=true)Integer goodsId,@RequestParam(required=true)Integer goodsNum) {
		ResultObj obj = null;
		try {
			String goodsIdsJson = CookieUtil.getCookieValue(request, "goodsIds");
			List<Integer> goodsIds = JSON.parseArray(goodsIdsJson, Integer.class);
			if(null == goodsIds) {
				goodsIds = new ArrayList<>();
			}
			
			if(!goodsIds.contains(goodsId)) {
				goodsIds.add(goodsId);
			}
			
			String goodsItemJson = CookieUtil.getCookieValue(request, "goodsItem");
			Map<Integer,Integer> goodsItem = JSON.parseObject(goodsItemJson, Map.class);
			if(null == goodsItem) {
				goodsItem = new HashMap<>();
			}
			
			Integer lastNumber = 0;
			if(goodsItem.containsKey(goodsId)) {
				lastNumber = goodsItem.get(goodsId);
			}
			goodsItem.put(goodsId, lastNumber+goodsNum);
			
			CookieUtil.setCookie(request, response, "goodsIds", JSON.toJSONString(goodsIds));
			CookieUtil.setCookie(request, response, "goodsItem", JSON.toJSONString(goodsItem));
			
			obj = new ResultObj(200, "购物车添加成功");
			
		}catch (Exception e) {
			obj = new ResultObj(-1, "购物车添加失败");
			e.printStackTrace();
			
		}
		
		return obj;
	}
	
	@RequestMapping("/cart/getTotal")
	@ResponseBody
	public Long getTotal(HttpServletRequest request) {
		long total = 0L;
		
		String goodsItemJson = CookieUtil.getCookieValue(request, "goodsItem");
		Map<Integer,Integer> goodsItem = JSON.parseObject(goodsItemJson, Map.class);
		if(null != goodsItem) {
			Set<Integer> ids = goodsItem.keySet();
			for (Integer id : ids) {
				total += goodsItem.get(id);
			}
		}
		
		return total;
	}
	
	@RequestMapping("cart/list/findByPage")
	@ResponseBody
	public SearchGoodsResult selectAllCartForPage(HttpServletRequest request,HttpServletResponse response,@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="5")Integer pageSize) {
		SearchGoodsResult searchGoodsResult = new SearchGoodsResult();
		
		String goodsIdsJson = CookieUtil.getCookieValue(request, "goodsIds");
		String goodsItemJson = CookieUtil.getCookieValue(request, "goodsItem");
		List<Integer> goodsIds = null ;
		final Map<Integer,Integer> goodsItem = new HashMap<>();
		if(null != goodsIdsJson && null != goodsItemJson) {
			goodsIds = JSON.parseArray(goodsIdsJson, Integer.class);
			goodsItem.putAll(JSON.parseObject(goodsItemJson, Map.class)); 
		}else {
			return null;
		}
		
		int start = (currentPage-1)*pageSize;
		int end = currentPage*pageSize-1>goodsIds.size()?goodsIds.size():currentPage*pageSize-1;
		List<Integer> currentIds = goodsIds.subList(start, end);
		List<Goods> goodss = goodsService.findByIds(currentIds);
		
		List<SearchGoodsVo> results = new ArrayList<>();
		
		goodss.forEach((goods)->{
			SearchGoodsVo searchGoodsVo = goodsToSearchGoodsVo(goods);
			/**
			 * 线程
			 * lomdba表达式闭包的原理     对象必须要确定只有一份  if else判断后它就认为可能会有多份报错
			 * foreach不会
			 */
			searchGoodsVo.setGoodsCommentCount(goodsItem.get(goods.getId()));// 数量的设置
			results.add(searchGoodsVo);
		});
		
		searchGoodsResult.setResults(results);
		searchGoodsResult.setPage(currentPage);
		searchGoodsResult.setTotal((long) goodsIds.size());
		
		return searchGoodsResult;
	}
	
	private SearchGoodsVo goodsToSearchGoodsVo(Goods goods) {
		SearchGoodsVo searchGoodsVo = new SearchGoodsVo();
		searchGoodsVo.setId(goods.getId().toString());
		searchGoodsVo.setGoodsNameHl(goods.getGoodsName());
		searchGoodsVo.setGoodsPrice(goods.getShopPrice().toString());
		searchGoodsVo.setGoodsImg(goods.getOriginalImg());
		return searchGoodsVo;
	}
	
}
