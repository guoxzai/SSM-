package com.gxz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gxz.domain.Goods;
import com.gxz.model.ResultObj;
import com.gxz.model.SearchGoodsResult;
import com.gxz.model.SearchGoodsVo;
import com.gxz.service.CartService;
import com.gxz.service.GoodsService;
import com.gxz.utils.CookieUtil;

@Controller
public class RedisCartController {

	@Reference
	private CartService cartService;
	@Reference
	private GoodsService goodsService;
	
	
	@RequestMapping("/cart/list")
	public String toCartListPage() {
		return "cart-list";
	}
	
	/**
	 * common.js向后台发请求设置cookie
	 * 添加导入
	 * jquery.cookie.js
		common.js
	 * 给前端写入Cookie
	 */ 
	@RequestMapping("/cart/getCookie")
	@ResponseBody
	public ResultObj getCartLabel(HttpServletRequest request,HttpServletResponse response) {
	   String label = UUID.randomUUID().toString();
		CookieUtil.setCookie(request, response, "EGO-CART-LABLE", label,true);
		return new ResultObj(200, "cookie设置成功");
	}
	 
	@RequestMapping("/cart/add")
	@ResponseBody
	public ResultObj addCart(HttpServletRequest request,@RequestHeader("label")String label,@RequestParam(required=true)Integer goodsId,@RequestParam(required=true)Integer goodsNum) {
		//添加时通过浏览器中的cookie来判断用户是否登录
		String token = CookieUtil.getCookieValue(request, "X-TOKEN");
		
		ResultObj obj = null;
		try {
			long total = cartService.getTotal( label, token);
			if(total>100) {
				return obj = new ResultObj(400, "添加购物车失败");
			}
			
			cartService.addCart(goodsId, goodsNum, label, token);
			obj = new ResultObj(200, "添加购物车成功");
		} catch (Exception e) {
			e.printStackTrace();
			obj = new ResultObj(-1, "添加购物车失败");
		}
		
		return obj;
	}
	
	@RequestMapping("/cart/getTotal")
	@ResponseBody
	public Long getTotal(HttpServletRequest request,@RequestHeader("label") String label) {
		//添加时通过浏览器中的cookie来判断用户是否登录
		String token = CookieUtil.getCookieValue(request, "X-TOKEN");
		
		return cartService.getTotal(label,token);
	}
	
	@RequestMapping("cart/list/findByPage")
	@ResponseBody
	public SearchGoodsResult selectAllCartForPage(HttpServletRequest request,@RequestHeader("label")String label) {
		SearchGoodsResult searchGoodsResult = new SearchGoodsResult();
		
		String token = CookieUtil.getCookieValue(request, "X-TOKEN");
		
		Map<String,String> goodsInfo = cartService.getGoodsInfo(label,token);
		Set<String> set = goodsInfo.keySet();
		List<Integer> goodsIds = new ArrayList<>();//商品Id 
		for (String id : set) {
			goodsIds.add(Integer.parseInt(id));
		}
		
		Map<Integer,Goods> goodsInfoMap = new HashMap<>();  
		List<Goods> goodss = goodsService.findByIds(goodsIds);   
		
		List<SearchGoodsVo> searchGoodsVos = new ArrayList<>();
		goodss.forEach((goods)->{
			goodsInfoMap.put(goods.getId(), goods);
		});
		goodsIds.forEach((goodsId)->{
			Goods goods = goodsInfoMap.get(goodsId);
			SearchGoodsVo searchGoodsVo = goodsToSearchGoodsVo(goods);
			searchGoodsVo.setGoodsCommentCount(Integer.valueOf(goodsInfo.get(goodsId+"")));
			searchGoodsVos.add(searchGoodsVo);
		});

		searchGoodsResult.setResults(searchGoodsVos);
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
