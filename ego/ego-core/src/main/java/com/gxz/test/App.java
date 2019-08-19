package com.gxz.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gxz.test.domain.User;
import com.gxz.test.mapper.UserMapper;
import com.gxz.test.service.impl.UserServiceImpl;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-*.xml");
		UserMapper userMapper = ac.getBean(UserMapper.class);
		List<User> list = userMapper.selectByExample(null);
		list.forEach((user)->{
			System.out.println(user);
		});
		UserServiceImpl userService = ac.getBean(UserServiceImpl.class);
		User user = userService.find(2);
		System.out.println(user);
	}
}
