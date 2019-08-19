package com.gxz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gxz.domain.Goods;
import com.gxz.model.ResultObj;
import com.gxz.model.SearchGoodsResult;
import com.gxz.model.SearchGoodsVo;
import com.gxz.service.GoodsService;

//@Controller
public class SessionCartController {
	
	@Reference
	private GoodsService goodsService;
	
	@RequestMapping("/cart/list")
	public String toCartListPage() {
		return "cart-list";
	}
	
	//"goodsId":goodsId,"goodsNum":goodsNum
	@RequestMapping("/cart/add")
	@ResponseBody
	public ResultObj addCart(HttpServletRequest request,@RequestParam(required=true)Integer goodsId,@RequestParam(required=true)Integer goodsNum) {
		ResultObj obj = null;
		try {
			//存放商品id  通过Id就可以解决  sku定死的量
			List<Integer> goodsIds = (List<Integer>) request.getSession().getAttribute("goodsIds");
			if(null == goodsIds) {
				goodsIds = new ArrayList<>();
			}
			if(!goodsIds.contains(goodsId)) {
				goodsIds.add(goodsId);
			}
			
			//存放商品id  对应的数量      解决spu的选择组合
			Map<Integer,Integer> goodsItem = (Map<Integer, Integer>) request.getSession().getAttribute("goodsItem");
			if(null == goodsItem) {
				goodsItem = new HashMap<>();
			}
			
			Integer goodsLastNum = 0;
			if(goodsItem.containsKey(goodsId)) {
				goodsLastNum = goodsItem.get(goodsId);
			}
			goodsItem.put(goodsId, goodsLastNum+goodsNum);
			
			request.getSession().setAttribute("goodsIds", goodsIds);
			request.getSession().setAttribute("goodsItem", goodsItem);
			
			obj = new ResultObj(200, "购物车添加成功");
		}catch (Exception e) {
			obj = new ResultObj(-1, "购物车添加失败");
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * 获取购物车里面商品的总数量
	 * @return
	 */
	@RequestMapping("/cart/getTotal")
	@ResponseBody
	public Long getTotal(HttpSession session) {
		Map<Integer,Integer> goodsItem = (Map<Integer, Integer>) session.getAttribute("goodsItem");
		long total = 0L;
		if(null != goodsItem) {
			Set<Integer> goodsIds = goodsItem.keySet();
			for (Integer goodsId : goodsIds) {
				total += goodsItem.get(goodsId);
			}
		}
		
		return total;
	}
	
	@RequestMapping("cart/list/findByPage")
	@ResponseBody
	public SearchGoodsResult selectAllCartForPage(HttpSession session,@RequestParam(defaultValue="1")Integer currentPage,@RequestParam(defaultValue="5")Integer pageSize) {
		SearchGoodsResult searchGoodsResult = new SearchGoodsResult();
		
		List<Integer> goodsIds = (List<Integer>) session.getAttribute("goodsIds");
		Map<Integer,Integer> goodsItem = (Map<Integer, Integer>) session.getAttribute("goodsItem");
		
		//////////// 111 114  112   115   116   190  120
	    //////////// 0   1    2     3    4     5     6  6 6 null 7
	    /**
	     * 利用subList 实现分页
	     */
		Integer start = (currentPage-1)*pageSize;
		Integer end = currentPage*pageSize-1>goodsIds.size()?goodsIds.size():currentPage*pageSize-1;
		//实现分页
		List<Integer> goodsIdsForPage = goodsIds.subList(start, end);
		List<Goods> goodss = goodsService.findByIds(goodsIdsForPage);
		
		List<SearchGoodsVo> results = new ArrayList<>();
		goodss.forEach((goods)->{
			SearchGoodsVo searchGoodsVo = goodsToSearchGoodsVo(goods);
			searchGoodsVo.setGoodsCommentCount(goodsItem.get(goods.getId()));// 数量的设置
			results.add(searchGoodsVo);
			searchGoodsResult.setResults(results);
		});
		
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
