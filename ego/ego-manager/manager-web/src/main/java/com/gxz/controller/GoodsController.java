package com.gxz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gxz.constant.SysConstant;
import com.gxz.domain.Goods;
import com.gxz.domain.GoodsAttribute;
import com.gxz.model.EasyUIDataGrid;
import com.gxz.model.EasyUITree;
import com.gxz.model.GoodsAttrGroup;
import com.gxz.model.GoodsAttrResult;
import com.gxz.model.GoodsVo;
import com.gxz.model.PageBean;
import com.gxz.model.ResultObj;
import com.gxz.model.TreeNode;
import com.gxz.service.GoodsAttrService;
import com.gxz.service.GoodsAttributeService;
import com.gxz.service.GoodsCatService;
import com.gxz.service.GoodsService;
import com.gxz.service.SolrImportService;


//不能用goods全局mapping  不然路由路由不到
@RestController
public class GoodsController {

	@Reference
	private GoodsService goodsService;
	@Reference
	private GoodsCatService goodsCatService;
	@Reference
	private GoodsAttributeService goodsAttributeService;
	@Reference
	private GoodsAttrService goodsAttrService;
	
	//solr的手动数据导入
	@Reference
	private SolrImportService solrImportService;
	
	@GetMapping("/{id}")
	public Goods getGoods(@PathVariable("id") Integer id) {
		return goodsService.find(id);
	}
	
	@GetMapping("/goods/getData")
	public EasyUIDataGrid loadAllGoods(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows) {
		PageBean<Goods> pageBean = goodsService.findAll(page, rows);
		return new EasyUIDataGrid(pageBean.getTotal(), pageBean.getData());
	}
	
	/*************商品类别查询    树的操作开始************/
	//懒加载
	//前台是post请求没有办法GetMapping
	@RequestMapping("/goods/cat/list")
	public List<EasyUITree> loadGoodsCateTreeByPid(@RequestParam(defaultValue="0")Integer id){
		//数据库查询的  简单json格式
		List<TreeNode> treeNodes = goodsCatService.loadTreeByPid(id);
		//装换为前台需要的标准json格式
		List<EasyUITree> easyUITree = EasyUITree.treeChangeEasyUITree(treeNodes);
		return easyUITree;
	}
	/*************商品类别查询    树的操作结束************/
	
	/*************树CRUD的管理    开始****************/ 
	/**
	 * 添加修改简单的操作      ContentController对树的具体DML操作
	 * @return
	 */
	//全加载  Demo
	@RequestMapping("/tress")
	public List<TreeNode> loadAllTree(){
		List<TreeNode> nodes = goodsCatService.loadAllTree();
		return nodes;
	}
	
	@RequestMapping("/goods/cat/saveOrUpdate")
	public ResultObj updateGoodsCategory(TreeNode treeNode) {
		ResultObj obj = null;
		if(treeNode.getParentId() != null) {
			try {
				goodsCatService.add(treeNode);
				obj = new ResultObj(200,SysConstant.OPERATE_ADD_SUCCESS);
			}catch(Exception e) {
				obj = new ResultObj(-1,SysConstant.OPERATE_ADD_ERROR);
				e.printStackTrace();
			}
		}else if(treeNode.getId() != null) {
			try {
				goodsCatService.update(treeNode);
				obj = new ResultObj(200,SysConstant.OPERATE_UPDATE_SUCCESS);
			}catch(Exception e) {
				obj = new ResultObj(-1,SysConstant.OPERATE_UPDATE_ERROR);
				e.printStackTrace();
			}
		}
		
		return obj;
	}
	
	@RequestMapping("/goods/cat/delete")
	public ResultObj deleteGoodsCategory(@RequestParam(required=true) Integer id) {
		ResultObj obj = null;
		try {
			Integer result = goodsCatService.delete(id);
			obj = result!=-1? new ResultObj(200,SysConstant.OPERATE_DELETE_SUCCESS): new ResultObj(-1,"该节点还有子节点");
			
		}catch(Exception e) {
			obj = new ResultObj(-1,SysConstant.OPERATE_DELETE_ERROR);
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/*************树CRUD的管理    结束****************/ 
	
	/*************规格参数的管理  开始****************/
	@GetMapping("/goods/param/list")
	public EasyUIDataGrid loadAllGoodsAttr(@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows) {
		PageBean<GoodsAttribute> pageBean = goodsAttributeService.findAll(page, rows);
		List<GoodsAttribute> data = pageBean.getData();
		return new EasyUIDataGrid(pageBean.getTotal(), data);
	}
	
	@PostMapping("goods/param/save/{typeId}")
	public ResultObj addGoodsAttr(@PathVariable("typeId") Integer typeId,GoodsAttribute goodsAttr) {
		ResultObj obj = null;
		try {
			goodsAttr.setTypeId(typeId.shortValue());
			goodsAttributeService.add(goodsAttr);
			obj = new ResultObj(200,SysConstant.OPERATE_ADD_SUCCESS);
			
		} catch (Exception e) {
			obj = new ResultObj(-1,SysConstant.OPERATE_ADD_ERROR);
			
		}
		return obj;
	}
	
	//商品添加时回显    拼json
	@GetMapping("/goods/param/query/itemcatid/{typeId}")
	public Object loadGoodsAttrByTypeId(@PathVariable("typeId")Integer typeId) {
		/**
		 * 查询需要注意大数据类型   values
		 */
		List<GoodsAttribute> goodsAtts = goodsAttributeService.findByTypeId(typeId);
		
		GoodsAttrResult goodsAttrResult = new GoodsAttrResult();
		if(null != goodsAtts && goodsAtts.size()>0) {
			goodsAttrResult.setStatus(200);
			Map<String, List<GoodsAttrGroup>> data = new HashMap<>();
			List<GoodsAttrGroup> groups = goodsAttrToGroup(goodsAtts);
			data.put("paramData", groups);
			goodsAttrResult.setData(data);
		}
		
		return goodsAttrResult;
	}
	
//	private String group;
//	private List<?> params;
	private List<GoodsAttrGroup> goodsAttrToGroup(List<GoodsAttribute> goodsAtts) {
		List<GoodsAttrGroup> groups = new ArrayList<>();

		for (GoodsAttribute goodsAttr : goodsAtts) {
			GoodsAttrGroup attrGroup = new GoodsAttrGroup();
			attrGroup.setCatId(goodsAttr.getId());
			attrGroup.setGroup(goodsAttr.getAttrName());
			//values 是text大数据类型
			String values = goodsAttr.getAttrValues();
			if(null != values && !"".equals(values.trim())) {
				attrGroup.setParams(Arrays.asList(values.split("\r\n")));
			}else {
				attrGroup.setParams(Arrays.asList("值"));
				
			}
			
			groups.add(attrGroup);
		}
				
		return groups;
	}
	
	//商品的添加
	/**
	 * 事务控制    事务合并
	 * goodsVo的封装  json  Goods
	 * @param itemParams
	 * @param goods
	 * @return
	 */
	@PostMapping("/goods/save")
	public ResultObj addGoods(String itemParams,Goods goods) {
		ResultObj obj = null;
		try {
			GoodsVo goodsVo = new GoodsVo();
			goodsVo.setItemParams(itemParams);
			goods.setUpdatetime(new Date());
			goodsVo.setGoods(goods);
			
			goodsService.addGoodsAndGoodsAttr(goodsVo);
			obj = new ResultObj(200,SysConstant.OPERATE_ADD_SUCCESS);
			
		}catch (Exception e) {
			obj = new ResultObj(-1,SysConstant.OPERATE_ADD_ERROR);
			
		}
		return obj;
	}
	
	/*************商品的修改******************/
	//商品修改时回显   富文本回显
	@GetMapping("/goods/query/desc/{id}")
	public Object loadGoodsContentById(@PathVariable("id")Integer id) {
		ResultObj obj = null;
		try {
			String goodsdesc = goodsService.queryGoodsContentById(id);
			obj = new ResultObj(200, goodsdesc);
		} catch (Exception e) {
			obj = new ResultObj(-1, "商品详情加载失败");
		}
		
		return obj;
	}
	
	//商品修改时回显   规格参数回显
	@GetMapping("/goods/attrparam/{id}")
	public GoodsAttrResult loadGoodsAttrParamById(@PathVariable("id")Integer goodsid) {
		List<GoodsAttrGroup> itemGroups = goodsAttrService.queryAttrGroupByGoodsId(goodsid);
		
		if(null == itemGroups) return null;
		
		GoodsAttrResult goodsAttr = new GoodsAttrResult();
		goodsAttr.setStatus(200);
		Map<String, List<GoodsAttrGroup>> data = new HashMap<>();
		data.put("params", itemGroups);
		goodsAttr.setData(data );
		
		return goodsAttr;
	}
	
	@RequestMapping("/goods/update")
	public ResultObj updateGoods(String itemParams,Goods goods) {
		ResultObj obj = null;
		try {
			GoodsVo goodsVo = new GoodsVo();
			goods.setUpdatetime(new Date());
			goodsVo.setItemParams(itemParams);
			goodsVo.setGoods(goods);
			
			goodsService.updateGoods(goodsVo);
			obj = new ResultObj(200, SysConstant.OPERATE_UPDATE_SUCCESS);
			
		} catch (Exception e) {
			obj = new ResultObj(-1, SysConstant.OPERATE_UPDATE_ERROR);
		}
		
		return obj;
	}
	
	/*************规格参数的管理  结束****************/
	
	//商品的删除
	@RequestMapping("/rest/goods/delete")
	public ResultObj deleteGoodsWrap(@RequestParam(required=true) String ids) {
		ResultObj obj = null;
		try {
			goodsService.deleteWrap(ids);
			obj = new ResultObj(200, SysConstant.OPERATE_DELETE_SUCCESS);
		}catch (Exception e) {
			obj = new ResultObj(-1, SysConstant.OPERATE_DELETE_ERROR);
		}
		
		return obj;
	} 
	
	//修改商品下架2
	@PostMapping("/rest/goods/instock")
	public ResultObj updateOnSale(@RequestParam(required=true) String ids) {
		ResultObj obj = null;
		try {
			goodsService.updateOnSale(ids);
			obj = new ResultObj(200, "商品已下架");
		}catch (Exception e) {
			obj = new ResultObj(-1, "系统错误");
		}
		
		return obj;
	}
	
	//修改商品上架1
	@PostMapping("/rest/goods/reshelf")
	public ResultObj updateDownSale(@RequestParam(required=true) String ids) {
		ResultObj obj = null;
		try {
			goodsService.updateDownSale(ids);
			obj = new ResultObj(200, "商品已下架");
		}catch (Exception e) {
			obj = new ResultObj(-1, "系统错误");
		}
		
		return obj;
	}
	
	/************************Solr导入商品****************/
	@RequestMapping("/solr/importDataFromMysql")
	public ResultObj importDataFromMysql() {
		ResultObj obj = null;
		try {
			long total = solrImportService.importGoodsDataForSolr();
			Map<String,Object> map = new HashMap<>();
			map.put("data", total);
			obj = new ResultObj(200, "导入数据成功");
		} catch (Exception e) {
			obj = new ResultObj(-1, "导入数据失败");
			e.printStackTrace();
		}
		return obj;
	}
}
