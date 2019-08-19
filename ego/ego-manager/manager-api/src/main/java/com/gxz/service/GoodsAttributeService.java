package com.gxz.service;

import java.util.List;

import com.gxz.domain.GoodsAttribute;
import com.gxz.model.GoodsAttrGroup;

public interface GoodsAttributeService extends IService<GoodsAttribute> {

	List<GoodsAttribute> findByTypeId(Integer typeId);


}
