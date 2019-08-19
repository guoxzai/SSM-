package com.gxz.test.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxz.service.impl.AServiceImpl;
import com.gxz.test.domain.User;
import com.gxz.test.mapper.UserMapper;
import com.gxz.test.service.UserService;

@Service
public class UserServiceImpl extends AServiceImpl<User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@PostConstruct
	public void setDAO() {
		super.dao = this.userMapper;
	}
	
}
