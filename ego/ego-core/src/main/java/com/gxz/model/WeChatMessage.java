package com.gxz.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信消息的封装
 * @author CodeLab
 *{
    "touser":"odGzM5luR2E98xNBUl3OZJexyCoA",
    "template_id":"x98NJDf3ngGvmzGy8yKISM1uLLm-ozOoOfY--4R7nRM",
    "data":{
       "user":{
           "value":"郭仔",
           "color":"#3300FF"
       },
       "code":{
        "value":"0819",
        "color":"#F70938"
    },
    "time":{
        "value":"1 分钟",
        "color":"#FFFF00"
    }
    }
}
 */
public class WeChatMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//	@JSONField(name="touser")
	@JsonProperty("touser")
	private String toUser;
	
//	@JSONField(name="template_id")
	@JsonProperty("template_id")
	private String templateId;
	
	private Map<String, Map<String, String>> data;
	
	public static Map<String,String> builderMessage(String value,String color) {
		Map<String,String> map = new HashMap<>();
		map.put("value", value);
		map.put("color", color);
		
		return map;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Map<String, Map<String, String>> getData() {
		return data;
	}

	public void setData(Map<String, Map<String, String>> data) {
		this.data = data;
	}

	public static void main(String[] args) {
		WeChatMessage message = new WeChatMessage();
		message.setToUser("oY-X11CvfoznTLUMkVCuiCXIL1ec");
		message.setTemplateId("eJJkLXzx-v_iHS1kBwb9fBUTqsCaL7JlcvFXBF3UxQY");
		Map<String,Map<String,String>> data = new HashMap<>();
		data.put("user", WeChatMessage.builderMessage("国仔", "#173177"));
		data.put("code", WeChatMessage.builderMessage("0406", "#173177"));
		data.put("time", WeChatMessage.builderMessage("1分钟", "#173177"));
		message.setData(data);
		
		String messageJson = JSON.toJSONString(message);
		System.out.println(messageJson);
	}
	
}
