package com.gxz.service;

import java.util.List;

import com.gxz.domain.Goods;
import com.gxz.model.GoodsVo;
import com.gxz.model.PageBean;

public interface GoodsService extends IService<Goods> {

	void addGoodsAndGoodsAttr(GoodsVo goodsVo);

	String queryGoodsContentById(Integer id);

	void updateGoods(GoodsVo goodsVo);

	void deleteWrap(String ids);

	void updateOnSale(String ids);

	void updateDownSale(String ids);

	PageBean<Goods> queryGoods(Integer catId, int page, int rows, boolean price, boolean comment, Integer min, Integer max);

	List<Goods> findByIds(List<Integer> goodsIdsForPage);

}
