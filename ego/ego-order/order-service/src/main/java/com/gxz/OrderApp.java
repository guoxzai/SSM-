package com.gxz;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class OrderApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		try {
			System.out.println("OrderApp 服务注册成功");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
