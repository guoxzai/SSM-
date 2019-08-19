package com.gxz.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.gxz.domain.GoodsAttribute;
import com.gxz.domain.GoodsAttributeExample;
import com.gxz.mapper.GoodsAttributeMapper;
import com.gxz.model.GoodsAttrGroup;
import com.gxz.service.GoodsAttributeService;


@Service
public class GoodsAttributeServiceImpl extends AServiceImpl<GoodsAttribute> implements GoodsAttributeService {

	@Autowired
	private GoodsAttributeMapper goodsAttributeMapper;
	
	@PostConstruct
	public void set() {
		super.dao = this.goodsAttributeMapper;
	}

	@Override
	public List<GoodsAttribute> findByTypeId(Integer typeId) {
		if(null == typeId) {
			throw new RuntimeException("typeId不能为空");
		}
		
		GoodsAttributeExample example = new GoodsAttributeExample();
		example.createCriteria().andTypeIdEqualTo(typeId.shortValue());
		List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByExampleWithBLOBs(example);
		
		return goodsAttributes;
	}

}
