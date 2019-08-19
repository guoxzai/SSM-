package com.gxz;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PayApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext app  = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		app.start();
		
		System.out.println("支付服务启动成功");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
