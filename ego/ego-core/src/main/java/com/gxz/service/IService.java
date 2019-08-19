package com.gxz.service;

import java.io.Serializable;

import com.gxz.model.PageBean;


public interface IService<T> {
	T add(T t);
	int delete(Serializable id);
	int update(T t);
	
	T find(Serializable id);
	PageBean<T> findAll(int page,int size);
}
