package com.gxz.service.impl;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gxz.dao.IDao;
import com.gxz.model.PageBean;
import com.gxz.service.IService;

public class AServiceImpl<T> implements IService<T> {

	protected IDao<T> dao;

	@Override
	public T add(T t) {
		if(null == t) {
			throw new RuntimeException("要插入的对象不能为空");
		}
		dao.insert(t);
		return t;
	}

	@Override
	public int delete(Serializable id) {
		if(null == id) {
			throw new RuntimeException("删除ID项不能为空");
		}

		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int update(T t) {
		if(null == t) {
			throw new RuntimeException("要修改的的对象不能为空");
		}
		return dao.updateByPrimaryKeySelective(t);
	}

	@Override
	public T find(Serializable id) {
		if(null == id) {
			throw new RuntimeException("查询单个对象Id不能为空");
		}

		return dao.selectByPrimaryKey(id);
	}

	@Override
	public PageBean<T> findAll(int page, int size) {
		//拦截器拦截Sql  进行分页
		Page<Object> pageHelper = PageHelper.startPage(page, size);
		List<T> data = dao.selectByExample(null);
		Long total = pageHelper.getTotal();
		return new PageBean<T>(page,size,total ,data);
	}

}
