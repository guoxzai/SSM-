package com.gxz.service;

import com.gxz.model.SearchGoodsResult;
import com.gxz.model.SearchGoodsVo;

public interface SearchGoodsService {

	SearchGoodsResult doSearchGoods(SearchGoodsVo searchGoodsVo,boolean price,boolean comment,Integer min,Integer max);
	
	SearchGoodsResult doQuery(Integer catId, int page, int rows, boolean price, boolean comment,Integer min, Integer max); 
	
}
