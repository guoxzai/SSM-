package com.gxz.service;

import java.util.List;

import com.gxz.domain.GoodsAttr;
import com.gxz.model.GoodsAttrGroup;

public interface GoodsAttrService extends IService<GoodsAttr> {

	void addGoodsAttr(List<GoodsAttrGroup> attrGroupJson, Integer id);

	List<GoodsAttrGroup> queryAttrGroupByGoodsId(Integer id);

	void updateGoodsAttr(List<GoodsAttrGroup> itemGroups, Integer id);

	void deleteByGoodsId(Integer goodsId);

}
