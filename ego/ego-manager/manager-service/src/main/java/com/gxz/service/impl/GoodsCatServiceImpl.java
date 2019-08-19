package com.gxz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.gxz.anno.DeleteCache;
import com.gxz.anno.EnableCache;
import com.gxz.domain.GoodsCategory;
import com.gxz.domain.GoodsCategoryExample;
import com.gxz.mapper.GoodsCategoryMapper;
import com.gxz.model.TreeNode;
import com.gxz.redis.RedisClient;
import com.gxz.service.GoodsCatService;

@Service(timeout=9000,retries=1,actives=100)
public class GoodsCatServiceImpl implements GoodsCatService {

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	//缓存代码侵入性很高的设置
	/*@Autowired
	private RedisClient redisClient;
	@Value("${ego.menu.data}")
	private String protalMenuDataKey;*/
	
	
	/*******树的懒加载   不加载子节点*************/
	
	@Override
	public List<TreeNode> loadTreeByPid(Integer pid) {
		if(null == pid) {
			throw new RuntimeException("父ID不能为空");
		}
		
		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andParentIdEqualTo(pid.shortValue());
		List<GoodsCategory> goodsCats = goodsCategoryMapper.selectByExample(example);
		
		List<TreeNode> treeNodes = new ArrayList<>();
		
		goodsCats.forEach((goodsCat)->{
			TreeNode treeNode = new TreeNode();
			treeNode.setId(goodsCat.getId().intValue());
			treeNode.setPid(goodsCat.getParentId().intValue());
			treeNode.setName(goodsCat.getName());
			treeNode.setIsOpen(goodsCat.getLevel()==3?true:false);
			treeNodes.add(treeNode);
		});
		
		return treeNodes;
	}

	/*******树的全局加载   不加载子节点*************/
	//代码侵入性很高的   加入缓冲
	/*@Override
	public List<TreeNode> loadAllTree() {
		List<TreeNode> treeNodes = new ArrayList<>();
		
		if(!redisClient.isExist(protalMenuDataKey)) {
			treeNodes = loadAllTree(0);
			String val = JSON.toJSONString(treeNodes);
			redisClient.set(protalMenuDataKey, val);
		}
		
		String val = redisClient.get(protalMenuDataKey);
		return JSON.parseArray(val, TreeNode.class);
	}*/
	@EnableCache(namespace="GoodsCatServiceImpl",label="EGO_MENU_DATA")
	@Override
	public List<TreeNode> loadAllTree() {
		List<TreeNode> trees = loadAllTree(0);
		return trees;
	}
	
	public List<TreeNode> loadAllTree(Integer pid){
		if(null == pid) pid = 0;
		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andParentIdEqualTo(pid.shortValue());
		List<GoodsCategory> goodsCats = goodsCategoryMapper.selectByExample(example);
		
		List<TreeNode> treeNodes = new ArrayList<>();
		
		goodsCats.forEach((goodsCat)->{
			TreeNode treeNode = new TreeNode();
			treeNode.setId(goodsCat.getId().intValue());
			treeNode.setPid(goodsCat.getParentId().intValue());
			treeNode.setName(goodsCat.getName());
			treeNode.setIsOpen(goodsCat.getLevel()==3?true:false);
			
			//递归调用
			treeNode.setChildrens(this.loadAllTree(goodsCat.getId().intValue()));
			treeNodes.add(treeNode);
			
		});
		
		return treeNodes;
	}

	@DeleteCache(namespace="GoodsCatServiceImpl",method="loadTreeByPid",label="#0.pid")
	@Override
	public Integer add(TreeNode treeNode) {
		if(treeNode==null||treeNode.getParentId()==null||treeNode.getName()==null) {
			throw new RuntimeException("添加节点时，必须提供相关的信息");
		}
		GoodsCategory goodsCat = new GoodsCategory();
		goodsCat.setParentId(treeNode.getParentId().shortValue());
		goodsCat.setName(treeNode.getName());
		goodsCat.setLevel((byte)3);//添加是文件
		
		Integer result = goodsCategoryMapper.insertSelective(goodsCat);
		return result;
	}

	@Override
	public Integer update(TreeNode treeNode) {
		if(treeNode==null||treeNode.getId()==null||treeNode.getName()==null) {
			throw new RuntimeException("修改节点时，必须提供相关的信息");
		}
		
		GoodsCategory goodsCat = new GoodsCategory();
		goodsCat.setId(treeNode.getId().shortValue());
		goodsCat.setName(treeNode.getName());
		
		Integer result = goodsCategoryMapper.updateByPrimaryKeySelective(goodsCat);
		return result;
	}

	@Override
	public Integer delete(Integer id) {
		if(null == id) {
			throw new RuntimeException("删除 ID不能为空!");
		}
		
		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andParentIdEqualTo(id.shortValue());
		long count = goodsCategoryMapper.countByExample(example);
		Integer result = count == 0L? goodsCategoryMapper.deleteByPrimaryKey(id.shortValue()):-1;
		return result;
	}

	/**
	 * SELECT
	id 
	FROM
	t_goods_category 
	WHERE
	parent_id IN ( SELECT id FROM t_goods_category WHERE parent_id = 1 );
	 */
	//前台protal  种类查询
	@Override
	public List<Integer> findSubIdsByParent(Integer catId) {
		GoodsCategory self = goodsCategoryMapper.selectByPrimaryKey(catId.shortValue());
		List<Integer> subIds = new ArrayList<>(); // 只装三级节点
		//判断前台是不是底层节点
		if(self.getLevel()==(byte)3) {
			subIds.add(catId);
		}else {
			GoodsCategoryExample example = new GoodsCategoryExample();
			example.createCriteria().andParentIdEqualTo(catId.shortValue()); // 设置parent的条件对象
			
			List<GoodsCategory> goodsCatParent = goodsCategoryMapper.selectByExample(example ); 
			for (GoodsCategory goodsCategory : goodsCatParent) {
				if(goodsCategory.getLevel()==(byte)3) {
					subIds.add(goodsCategory.getId().intValue()); // 添加三级节点
				}else {
					subIds.addAll(findSubIdsByParent(goodsCategory.getId().intValue())); // 递归查询子节点
				}
			}
		}
		return subIds;
	}
}
