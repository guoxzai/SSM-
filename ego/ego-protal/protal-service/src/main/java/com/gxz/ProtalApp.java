package com.gxz;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProtalApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		try {
			System.out.println("protalApp 服务注册成功");
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
