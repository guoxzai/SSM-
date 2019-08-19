package com.gxz;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SsoApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		try {
			System.out.println("SsoApp 服务注册成功");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
