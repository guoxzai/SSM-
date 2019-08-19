package com.gxz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.gxz.model.ResultObj;
import com.gxz.model.WeChatMessage;
import com.gxz.redis.RedisClient;
import com.gxz.service.LoginService;
import com.gxz.utils.AuthCodeUtil;
import com.gxz.utils.CookieUtil;

@Controller
public class LoginController {
	
	@Reference
	private LoginService loginService;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private RedisClient redisClient;
	
	//单点登录 
	@RequestMapping("/toLogin")
	public String toLogin(String lastUrl,Model model) {
		model.addAttribute("redirectUrl",lastUrl);
		return "login";
	}
	
	@RequestMapping("/user/sign")
	@ResponseBody
	public ResultObj writeToken(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=true)String username,@RequestParam(required=true)String password) {
		//测试单点登录
		//CookieUtil.setCookie(request, response, "X-TOKEN", "GXZ");
		String token = null;
		try {
			token = loginService.doLogin(username,password);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(null!=token) {
			// 用户登录成功后，将token写入cookie 里面。设置1天过期
			CookieUtil.setCookie(request, response, "X-TOKEN", token , 24*3600 ,true);
			
			//使用MQ及时感知用户登录   进行数据转移讲Redis中的数据转移到数据库中  提高实时性
			String label = CookieUtil.getCookieValue(request, "EGO-CART-LABLE");
			if(null != label && !"".equals(label)) {
				//在此给队列发送消息
				//消息生产者
				jmsTemplate.convertAndSend("user.login.queue",token+"#"+label);
			}
		}
		
		return new ResultObj(200, "登录成功");
	}
	
	/***********注册功能开始     微信发送验证码  消息队列***************/
	@RequestMapping("/toRegister")
	public String toRegister() {
		return "user-add";
	}
	
	@RequestMapping("/user/add")
	@ResponseBody
	public ResultObj registerUser(HttpServletResponse response,String username,String password,String code) {
		
		ResultObj validataCode = validataCode(username, code);
		
		if(validataCode.getStatus() == 200) {
			int result = loginService.addUser(username,password);
			if(result>0) {
				try {
					response.sendRedirect("http://sso.ego.com:8082/toLogin");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return new ResultObj(-1, "用户注册失败");
	}
	
	@RequestMapping("/auth/code")
	@ResponseBody
	public ResultObj getAuthcode(String username) {
		ResultObj obj = null;
		try {
			String code = AuthCodeUtil.generatorCode(6);
			
			WeChatMessage message = new WeChatMessage();
			message.setToUser(username);
			message.setTemplateId("eJJkLXzx-v_iHS1kBwb9fBUTqsCaL7JlcvFXBF3UxQY");
			Map<String,Map<String,String>> data = new HashMap<>();
			data.put("user", WeChatMessage.builderMessage("国仔", "#173177"));
			data.put("code", WeChatMessage.builderMessage(code, "#173177"));
			data.put("time", WeChatMessage.builderMessage("1分钟", "#173177"));
			message.setData(data);

			jmsTemplate.convertAndSend("wechat.message.quque", message);
			redisClient.set("Auth:"+username, code);
			redisClient.setExpire("Auth:"+username, 60*60);//设置3分钟过期
			
			obj = new ResultObj(200, "验证码已发送至你的手机");
			
		} catch (Exception e) {
			e.printStackTrace();
			obj = new ResultObj(-1, "验证码发送失败  请重试");
			
		}
		
		return obj;
	}
	
	/**
	 * 校验验证码
	 */
	@RequestMapping("/validata/code")
	@ResponseBody
	public ResultObj validataCode(String username,String code) {
		ResultObj obj = null;
		if(redisClient.isExist("Auth:"+username)) {
			String redisCode = redisClient.get("Auth:"+username);
			if(redisCode.equals(code)) {
				obj = new ResultObj(200, "验证码正确");
				
			}else {
				obj = new ResultObj(400, "验证码错误");
				
			}
			
		}else {
			obj = new ResultObj(400, "验证码无效  请重新获取");
		}
		
		return obj;
	}
	
	/***********注册功能结束  微信发送验证码  消息队列***************/
	
}
