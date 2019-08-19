package com.gxz.service;

import java.util.List;

import com.gxz.domain.Content;
import com.gxz.model.PageBean;

public interface ContentService extends IService<Content> {

	PageBean<Content> queryAllForPageById(Integer categoryId,Integer page,Integer size);

	List<Content> loadAdviceByCatId(Integer categoryId, Integer i);
	
}
