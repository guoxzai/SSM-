package com.gxz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.gxz.anno.EnableCache;
import com.gxz.domain.GoodsType;
import com.gxz.mapper.GoodsTypeMapper;
import com.gxz.service.GoodsTypeService;

@Service(timeout=7000)
public class GoodsTypeServiceImpl implements GoodsTypeService {

	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	
	@EnableCache(namespace="GoodsTypeServiceImpl",label="EGO_GOODS_TYPE")
	@Override
	public Map<Integer, String> selecAllMap() {
		Map<Integer,String> map = new HashMap<>();
		List<GoodsType> goodsTypes = goodsTypeMapper.selectByExample(null);
		for (GoodsType goodsType : goodsTypes) {
			map.put(goodsType.getId().intValue(), goodsType.getName());
		}
		return map;
	}

}
