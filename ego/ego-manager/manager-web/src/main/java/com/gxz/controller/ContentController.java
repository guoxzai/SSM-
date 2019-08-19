package com.gxz.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gxz.constant.SysConstant;
import com.gxz.domain.Content;
import com.gxz.domain.ContentCategory;
import com.gxz.model.EasyUIDataGrid;
import com.gxz.model.EasyUITree;
import com.gxz.model.PageBean;
import com.gxz.model.ResultObj;
import com.gxz.model.TreeNode;
import com.gxz.service.ContentCategoryService;
import com.gxz.service.ContentService;

@RestController
public class ContentController {

	@Reference
	private ContentCategoryService contentCategoryService;
	@Reference
	private ContentService contentService;

	@RequestMapping("/content/cat/getTree")
	public List<EasyUITree> loadAllContentCatTreeByPid(@RequestParam(defaultValue="0")Integer id){
		List<TreeNode> treeNodes = contentCategoryService.loadTreeByPid(id);
		List<EasyUITree> easyUITree = EasyUITree.treeChangeEasyUITree(treeNodes);
		return easyUITree;
	}
	
	/*@GetMapping("/content/getData")
	public EasyUIDataGrid loadAllDataGrid(@RequestParam(defaultValue="30")Integer categoryId,@RequestParam(defaultValue="1") Integer page,@RequestParam(defaultValue="10") Integer rows) {
		PageBean<ContentCategory> pageBean = contentCategoryService.queryAllForPage(categoryId,page, rows);
		return new EasyUIDataGrid(pageBean.getTotal(), pageBean.getData());
	}*/
	
	@RequestMapping("/content/cat/saveOrUpdate")
	public ResultObj updateGoodsCategory(TreeNode treeNode) {
		ResultObj obj = null;
		if(treeNode.getParentId() != null) {
			try {
				contentCategoryService.add(treeNode);
				obj = new ResultObj(200,SysConstant.OPERATE_ADD_SUCCESS);
			}catch(Exception e) {
				obj = new ResultObj(-1,SysConstant.OPERATE_ADD_ERROR);
				e.printStackTrace();
			}
		}else if(treeNode.getId() != null) {
			try {
				contentCategoryService.update(treeNode);
				obj = new ResultObj(200,SysConstant.OPERATE_UPDATE_SUCCESS);
			}catch(Exception e) {
				obj = new ResultObj(-1,SysConstant.OPERATE_UPDATE_ERROR);
				e.printStackTrace();
			}
		}
		
		return obj;
	}
	
	@RequestMapping("/content/cat/delete")
	public ResultObj deleteGoodsCategory(@RequestParam(required=true) Integer id) {
		ResultObj obj = null;
		try {
			Integer result = contentCategoryService.delete(id);
			obj = result!=-1? new ResultObj(200,SysConstant.OPERATE_DELETE_SUCCESS): new ResultObj(-1,"该节点还有子节点");
			
		}catch(Exception e) {
			obj = new ResultObj(-1,SysConstant.OPERATE_DELETE_ERROR);
			e.printStackTrace();
		}
		
		return obj;
	}
	
	@GetMapping("/content/getData")
	public EasyUIDataGrid loadAllContent(@RequestParam(required=true) Integer categoryId,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10") Integer rows) {
		PageBean<Content> pageBean = contentService.queryAllForPageById(categoryId, page, rows);
		if(null == pageBean) return null;
		return new EasyUIDataGrid(pageBean.getTotal(), pageBean.getData());
	}
	
	@RequestMapping("/content/saveOrUpdate")
	public ResultObj addContent(Content content) {
		ResultObj obj = null;
		try {
			content.setCreated(new Date());
			content.setUpdated(new Date());
			contentService.add(content);
			obj = new ResultObj(200,SysConstant.OPERATE_ADD_SUCCESS);
		}catch(Exception e) {
			obj = new ResultObj(-1,SysConstant.OPERATE_ADD_ERROR);
			e.printStackTrace();
		}
		
		return obj;
	}
}
