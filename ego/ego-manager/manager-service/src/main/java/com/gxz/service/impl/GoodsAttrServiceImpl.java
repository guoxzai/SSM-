package com.gxz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.gxz.domain.GoodsAttr;
import com.gxz.domain.GoodsAttrExample;
import com.gxz.mapper.GoodsAttrMapper;
import com.gxz.model.GoodsAttrGroup;
import com.gxz.service.GoodsAttrService;

@Service
public class GoodsAttrServiceImpl extends AServiceImpl<GoodsAttr> implements GoodsAttrService {
	
	@Autowired
	private GoodsAttrMapper goodsAttrMapper;
	
	@PostConstruct
	public void set() {
		super.dao = this.goodsAttrMapper;
	}

	@Transactional
	@Override
	public void addGoodsAttr(List<GoodsAttrGroup> attrGroupJson, Integer id) {
		for (GoodsAttrGroup goodsAttrGroup : attrGroupJson) {
			GoodsAttr goodsAttr = new GoodsAttr();
			goodsAttr.setGoodsId(id);
			goodsAttr.setAttrId(goodsAttrGroup.getCatId().shortValue());
			
			StringBuilder str = new StringBuilder();
			str.append("{\""+goodsAttrGroup.getGroup()+"\":");
			str.append(JSON.toJSONString(goodsAttrGroup.getParams()));
			str.append("}");
			goodsAttr.setAttrValue(str.toString());
			
			goodsAttrMapper.insertSelective(goodsAttr);
		}
	}

	//修改时   属性规格参数的回显
	@Override
	public List<GoodsAttrGroup> queryAttrGroupByGoodsId(Integer goodsId) {
		List<GoodsAttrGroup> attrGroups = new ArrayList<>();
		
		GoodsAttrExample example = new GoodsAttrExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		//json数据是大数据
		List<GoodsAttr> goodsAttrs = goodsAttrMapper.selectByExampleWithBLOBs(example);
		
		for (GoodsAttr goodsAttr : goodsAttrs) {
			attrGroups.add(goodsAttrToAttrGroup(goodsAttr));
		}
		
		return attrGroups;
	}

	private GoodsAttrGroup goodsAttrToAttrGroup(GoodsAttr goodsAttr) {
		GoodsAttrGroup attrGroup = new GoodsAttrGroup();
		attrGroup.setCatId(goodsAttr.getAttrId().intValue());
		
		String attrValue = goodsAttr.getAttrValue();
		Map map = JSON.parseObject(attrValue, Map.class);
		Set set = map.keySet();
		String group = "";
		String jsonParam = "";
		for (Object k : set) {
			group = k.toString();
			jsonParam = map.get(k).toString();
		}
		List<Object> params = JSON.parseArray(jsonParam, Object.class);
		attrGroup.setGroup(group);
		attrGroup.setParams(params);
		
		return attrGroup;
	}

	@Transactional
	@Override
	public void updateGoodsAttr(List<GoodsAttrGroup> itemGroups, Integer goodsId) {
		for (GoodsAttrGroup itemGroup : itemGroups) {
			// 如何修改商品的规格参数  通过一goods_id + attr_id 可以确定一个唯一的值
			Integer catId = itemGroup.getCatId();
			GoodsAttrExample example = new GoodsAttrExample();
			example.createCriteria(). // 通过一goods_id + attr_id 可以确定一个唯一的值
			andAttrIdEqualTo(catId.shortValue()).
			andGoodsIdEqualTo(goodsId);
			/**
			 * 拼接字符
			 */
			StringBuffer sb = new StringBuffer("{\""+itemGroup.getGroup()+"\":") ;
			sb.append(JSON.toJSONString(itemGroup.getParams()));
			sb.append("}");
			List<GoodsAttr> goodsAttrs = goodsAttrMapper.selectByExample(example); // 查询
			if(goodsAttrs!=null&&goodsAttrs.size()>0) {
				GoodsAttr goodsAttr = goodsAttrs.get(0);
				goodsAttr.setAttrValue(sb.toString());
				// 不是安全的修改
				goodsAttrMapper.updateByPrimaryKeyWithBLOBs(goodsAttr);
				
			}
		}
	}

	@Transactional
	@Override
	public void deleteByGoodsId(Integer goodsId) {
		GoodsAttrExample example = new GoodsAttrExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		goodsAttrMapper.deleteByExample(example);
	}

}
