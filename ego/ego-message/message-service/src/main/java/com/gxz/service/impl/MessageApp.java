package com.gxz.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.gxz.model.WeChatMessage;


public class MessageApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ac.start();
		/*************微信单独测试***********/
//		WeChatMessageServiceImpl bean = ac.getBean(WeChatMessageServiceImpl.class);
//		WeChatMessage message = new WeChatMessage();
//		message.setToUser("oY-X11CvfoznTLUMkVCuiCXIL1ec");
//		message.setTemplateId("eJJkLXzx-v_iHS1kBwb9fBUTqsCaL7JlcvFXBF3UxQY");
//		Map<String,Map<String,String>> data = new HashMap<>();
//		data.put("user", WeChatMessage.builderMessage("Eclipse测试", "#173177"));
//		data.put("code", WeChatMessage.builderMessage("0406", "#173177"));
//		data.put("time", WeChatMessage.builderMessage("1分钟", "#173177"));
//		message.setData(data);
//		
//		//给自动获取Token缓冲时间
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//		String messageJson = JSON.toJSONString(message);
//		bean.sendMessage(message);
//		
//		System.out.println(messageJson);
		try {
			System.out.println("ManagerApp 服务注册成功");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
