package com.gxz.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gxz.domain.Goods;
import com.gxz.domain.GoodsAttr;
import com.gxz.domain.GoodsAttrExample;
import com.gxz.domain.GoodsExample;
import com.gxz.domain.GoodsExample.Criteria;
import com.gxz.mapper.GoodsAttrMapper;
import com.gxz.mapper.GoodsMapper;
import com.gxz.model.GoodsAttrGroup;
import com.gxz.model.GoodsVo;
import com.gxz.model.PageBean;
import com.gxz.service.GoodsAttrService;
import com.gxz.service.GoodsCatService;
import com.gxz.service.GoodsService;

/**
 * @PostConstruct注解的方法将会在依赖注入完成后被自动调用。
 * 
 * @author User
 *
 */
@com.alibaba.dubbo.config.annotation.Service
public class GoodsServiceImpl extends AServiceImpl<Goods> implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsAttrMapper goodsAttrMapper;
	
	@Autowired
	private GoodsAttrService goodsAttrService;
	private GoodsCatService goodsCatService;

	@PostConstruct
	public void set() {
		super.dao = this.goodsMapper;
	}

	@Transactional
	@Override
	public void addGoodsAndGoodsAttr(GoodsVo goodsVo) {
		goodsMapper.insertSelective(goodsVo.getGoods());
		String itemParams = goodsVo.getItemParams();
		
		List<GoodsAttrGroup> attrGroupJson = JSON.parseArray(itemParams, GoodsAttrGroup.class);
		goodsAttrService.addGoodsAttr(attrGroupJson,goodsVo.getGoods().getId());
	}

	//修改时富文的回显
	@Override
	public String queryGoodsContentById(Integer id) {
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		if(null == goods) {
			throw new RuntimeException("商品信息不存在");
		}
		return goods.getGoodsContent();
	}

	@Transactional
	@Override
	public void updateGoods(GoodsVo goodsVo) {
		super.add(goodsVo.getGoods());
		List<GoodsAttrGroup> itemGroups = JSON.parseArray(goodsVo.getItemParams(), GoodsAttrGroup.class);
		goodsAttrService.updateGoodsAttr(itemGroups,goodsVo.getGoods().getId());
	}

	@Transactional
	@Override
	public void deleteWrap(String ids) {
		if(null == ids) {
			throw new RuntimeException("批量删除时ID不能为空");
		}
		
		String[] split = ids.split(",");
		for (String strId : split) {
			Integer goodsId = Integer.parseInt(strId);
			goodsMapper.deleteByPrimaryKey(goodsId);
			
			GoodsAttrExample example = new GoodsAttrExample();
			example.createCriteria().andGoodsIdEqualTo(goodsId);
			List<GoodsAttr> GoodsAttr = goodsAttrMapper.selectByExample(example );
			if(null !=GoodsAttr) {
				goodsAttrService.deleteByGoodsId(goodsId);
			}
		}
		
	}

	@Override
	public void updateOnSale(String ids) {
		if(null == ids) {
			throw new RuntimeException("批量修改时ID不能为空");
		}
		
		String[] split = ids.split(",");
		for (String str : split) {
			Goods goods = new Goods();
			goods.setId(Integer.parseInt(str));
			goods.setIsOnSale((byte)2);
			super.dao.updateByPrimaryKeySelective(goods);
		}
		
	}

	@Override
	public void updateDownSale(String ids) {
		if(null == ids) {
			throw new RuntimeException("批量修改时ID不能为空");
		}
		
		String[] split = ids.split(",");
		for (String str : split) {
			Goods goods = new Goods();
			goods.setUpdatetime(new Date());
			goods.setId(Integer.parseInt(str));
			goods.setIsOnSale((byte)1);
			super.dao.updateByPrimaryKeySelective(goods);
		}
	}

	/**
	 * 前台protal主页根据种类列表查出商品
	 */
	@Override
	public PageBean<Goods> queryGoods(Integer catId, int page, int rows, boolean price, boolean comment, Integer min, Integer max) {
		List<Integer> allSubIds = goodsCatService.findSubIdsByParent(catId);
		GoodsExample goodsExample = new GoodsExample();
		Criteria createCriteria = goodsExample.createCriteria();
		if(allSubIds!=null) {
			createCriteria.andCatIdIn(allSubIds);
		}
		
		// 分页
		Page<Goods> pageBean = PageHelper.startPage(page, rows);
		// 排序
		if(price) {
			goodsExample.setOrderByClause("shop_price asc");
		}
		if(comment) {
			goodsExample.setOrderByClause("comment_count desc");
		}
		// 价格的区间查询
		if(min!=null&&max!=null) {
			createCriteria.andShopPriceBetween(new BigDecimal(min), new BigDecimal(max));
		}
		
		List<Goods> goods = dao.selectByExample(goodsExample);
		
		return new PageBean<>(page, rows, pageBean.getTotal(), goods);
	}

	/**
	 * 购物车的分页查询  商品
	 */
	@Override
	public List<Goods> findByIds(List<Integer> goodsIdsForPage) {
//		GoodsExample example = new GoodsExample();
//		example.createCriteria().andIdIn(goodsIdsForPage);
//		return goodsMapper.selectByExample(example );
		
		GoodsExample example = new GoodsExample();
		StringBuilder stringBuilder = new StringBuilder("FIELD(id");
		for (Integer id : goodsIdsForPage) {
			stringBuilder.append(","+id);
		}
		example.setOrderByClause(stringBuilder+")");
		example.createCriteria().andIdIn(goodsIdsForPage);
//		select * FROM t_goods WHERE id in (119,1,115,121,137) ORDER BY FIELD(id,119,1,115,121,137);
		return goodsMapper.selectByExample(example );
	}
	
}
