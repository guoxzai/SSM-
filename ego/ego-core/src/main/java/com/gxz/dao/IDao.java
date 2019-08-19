package com.gxz.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {
	 int insert(T t);
	 
	 int deleteByPrimaryKey(Serializable id);
	 
	 int updateByPrimaryKeySelective(T t);
	 int updateByPrimaryKey(T t);
	 
	 T selectByPrimaryKey(Serializable id);

	 List<T> selectByExample(Object object);
}
