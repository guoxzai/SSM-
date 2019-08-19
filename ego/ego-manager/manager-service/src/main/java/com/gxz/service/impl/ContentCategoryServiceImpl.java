package com.gxz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.gxz.domain.ContentCategory;
import com.gxz.domain.ContentCategoryExample;
import com.gxz.mapper.ContentCategoryMapper;
import com.gxz.model.TreeNode;
import com.gxz.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public List<TreeNode> loadTreeByPid(Integer pid) {
		if (null == pid) {
			throw new RuntimeException("懒加载  父ID不能为空");
		}

		ContentCategoryExample example = new ContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(pid.longValue());
		List<ContentCategory> contentCats = contentCategoryMapper.selectByExample(example);

		List<TreeNode> treeNodes = new ArrayList<>();
		contentCats.forEach((contentCat) -> {
			TreeNode node = new TreeNode();
			node.setId(contentCat.getId().intValue());
			node.setPid(contentCat.getIsParent().intValue());
			node.setName(contentCat.getName());
			node.setIsOpen(contentCat.getIsParent() == 0 ? true : false);

			treeNodes.add(node);
		});

		return treeNodes;
	}

	@Override
	public List<TreeNode> loadAllTree() {
		// TODO Auto-generated method stub
		return null;
	}

	//添加站在文件角度考虑
	@Override
	public Integer add(TreeNode treeNode) {
		//选择文件夹添加文件
		ContentCategory categoryParent = contentCategoryMapper.selectByPrimaryKey(treeNode.getParentId().longValue());
		if(null == categoryParent) {
			throw new RuntimeException("请指定所属文件夹再添加");
		}
		
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setName(treeNode.getName());
		contentCategory.setParentId(treeNode.getParentId().longValue());
		contentCategory.setStatus(1);
		contentCategory.setIsParent((byte)0);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		int result = contentCategoryMapper.insertSelective(contentCategory);
		
		//判断子节点  所属的父节点的状态    0文件  1文件夹
		if(result>0) {
			if(categoryParent.getIsParent()==(byte)0) {
				categoryParent.setIsParent((byte)1);
				categoryParent.setUpdated(new Date());
				
				result = contentCategoryMapper.updateByPrimaryKeySelective(categoryParent);
			}
		}
		
		return result;
	}

	@Override
	public Integer update(TreeNode treeNode) {
		if(null == treeNode || treeNode.getId() == null || treeNode.getName() == null) {
			throw new RuntimeException("修改数据不存在");
		}
		
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setId(treeNode.getId().longValue());
		contentCategory.setName(treeNode.getName());
		contentCategory.setUpdated(new Date());
		
		return contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
	}

	
	//考虑删除文件或文件夹的情况
	@Override
	public Integer delete(Integer id) {
		//删除节点
		ContentCategory self = contentCategoryMapper.selectByPrimaryKey(id.longValue());
		if(null == self) {
			throw new RuntimeException("该参数不合法");
		}
		
		//先删除自己
		int result = contentCategoryMapper.deleteByPrimaryKey(self.getId());
		
		if(result > 0) {
			//考虑删除自己后   父节点的状态
			handlerParent(self);
			//删除子节点
			handlerChildren(self);
		}
		
		return result;
	}

	private void handlerChildren(ContentCategory self) {
		/**
		 * 不合适的做法
		 * 原因： 在for 循环里面不能发sql ，因为sql的数量未知
		 * delete from t_context_cat where id = x;
		 * delete from t_context_cat where id in (x,x1,x2);
		 *     对直接的儿子做了删除，但是间接的儿子没有做处理
		 *     我们也需要获取间接的儿子的节id 来删除
		 */
		List<Long> ids = getChilderSonIds(self.getId());
		if(null != ids && ids.size()>0) {
			ContentCategoryExample contentCategoryExample = new ContentCategoryExample();
			contentCategoryExample.createCriteria().andIdIn(ids);
			contentCategoryMapper.deleteByExample(contentCategoryExample);
		}
		
	}

	//获取  直接儿子和间接儿子
	private List<Long> getChilderSonIds(Long id) {
		List<Long> childrenIds = new ArrayList<>();
		ContentCategoryExample childrenExample = new ContentCategoryExample();
		childrenExample.createCriteria().andParentIdEqualTo(id);
		List<ContentCategory> sons = contentCategoryMapper.selectByExample(childrenExample);
		
		for (ContentCategory contentCategory : sons) {
			// 直接的儿子
			childrenIds.add(contentCategory.getId());
			// 间接的儿子
			childrenIds.addAll(getChilderSonIds(contentCategory.getId()));
		}
		
		return childrenIds;
	}

	private void handlerParent(ContentCategory self) {
		ContentCategoryExample example = new ContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(self.getParentId());
		long childrenCount = contentCategoryMapper.countByExample(example);
		// 1对父的影响 若父里面只有一个子，则该父变为子
		if(childrenCount == 0L) {// 没有子节点了，将它变为子节点
			ContentCategory fatherNode = new ContentCategory();
			fatherNode.setId(self.getParentId());
			fatherNode.setIsParent((byte)0);
			contentCategoryMapper.updateByPrimaryKeySelective(fatherNode);
		}
		
	}

}
