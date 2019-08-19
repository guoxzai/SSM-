package com.gxz.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gxz.anno.EnableCache;
import com.gxz.domain.Content;
import com.gxz.domain.ContentExample;
import com.gxz.mapper.ContentMapper;
import com.gxz.model.PageBean;
import com.gxz.service.ContentService;

@Service
public class ContentServiceImpl extends AServiceImpl<Content> implements ContentService {

	@Autowired
	private ContentMapper contentMapper;
	
	@PostConstruct
	public void set() {
		super.dao = this.contentMapper;
	}
	
	@Override
	public PageBean<Content> queryAllForPageById(Integer categoryId, Integer page, Integer size) {
		if(null == categoryId) {
			throw new RuntimeException("分类的ID不能为空");
		}
		
		Page<Object> pageBean = PageHelper.startPage(page, size);
		
		ContentExample example = new ContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId.longValue());
		List<Content> data = contentMapper.selectByExample(example);
		long total = pageBean.getTotal();
		return new PageBean<Content>(page, size, total, data);
	}

	@EnableCache(namespace="ContentServiceImpl",label="#0")
	@Override
	public List<Content> loadAdviceByCatId(Integer categoryId, Integer showCount) {
		ContentExample example = new ContentExample();
		example.setOrderByClause("updated desc");
		example.createCriteria().andCategoryIdEqualTo(categoryId.longValue());
		
		PageHelper.startPage(1,showCount,false);
		List<Content> data = contentMapper.selectByExampleWithBLOBs(example );
		
		return data;
	}

}
