package com.gxz.service.impl;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gxz.model.TokenResutl;
import com.gxz.model.WeChatMessage;
import com.gxz.service.WeChatMessageService;


/**
 * 1 获取模板id
 * 2 给微信服务器发一个post 请求
 * https://api.weixin.qq.com/cgi-bin/message/template/send
 * 3 json 数据
 * 
 * 
 *	java 发http 请求
 *  httpClient apache  抢票 爬虫
 *  okHttp 私人 移动端的发请求 java
 *  RestTemplate spring 提交一个接口，底层使用httpClient/okHttp
 *
 */
@Service
public class WeChatMessageServiceImpl implements WeChatMessageService,MessageListener {

	@Autowired
	private RestTemplate restTemplate;

	/**************定时发送   确定token权限**************/
	@Value("${wechat.token.url}")
	private String tokenUrl;
	
	@Value("${user.appid}")
	private String appId;
	
	@Value("${user.appsecret}")
	private String appsecret;
	
	
	private String token;
	
	/****************发送消息**********************/
	@Value("${wechat.msg.url}")
	private String messageUrl;
	
	
	/**
	 * 通过消息队列    
	 * 解决高耦合的问题  提高效率
	 */
	@JmsListener(destination="wechat.message.quque",containerFactory="containerFactory",concurrency="3-5")
	@Override
	public void onMessage(Message message) {
		ObjectMessage objMessage = (ObjectMessage)message;
		WeChatMessage weChatMessage = null;
		try {
			weChatMessage = (WeChatMessage) objMessage.getObject();
			sendMessage(weChatMessage);
			message.acknowledge();
			
		} catch (Exception e) {
			if(e instanceof JMSException) {
				System.out.println(message);
			}else {
				int i=3;
				while(i>0) {  //重试机制
					try {
						sendMessage(weChatMessage);
						break;
					} catch (Exception e2) {
						i--;
					}
					try {
						message.acknowledge();
					} catch (JMSException e1) {
						e1.printStackTrace();
					}
				}
			}
			e.printStackTrace();
		}
		
	}
	
	//基于okHttp演变过来的
	@Override
	public void sendMessage(WeChatMessage wechatMessage) {
		System.out.println(token);
		messageUrl = messageUrl.replaceAll("ACCESS_TOKEN", token);
		/**
		 * url : 发送到那个url
		 * Object object restTempalte 将该对象自动转换为json对象，使用jackson 转换
		 * responseType: 返回值类型，restTempalte 发请求后得到一个byte ->String（json）->对象
		 */
		String result = restTemplate.postForObject(messageUrl, wechatMessage, String.class);
		System.out.println(result);
	}
	
	//https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140183
	@Scheduled(fixedRate=7199*1000)
	public void refreshToken() {
		tokenUrl = tokenUrl.replaceAll("APPID", appId).replaceAll("APPSECRET", appsecret);
		TokenResutl tokenResutl = restTemplate.getForObject(tokenUrl, TokenResutl.class);
	    System.out.println(tokenResutl.getAccessToken());
		token = tokenResutl.getAccessToken();
	    System.out.println(token);
	}

	/*public static void main(String[] args) {
		rest();
	}
	
	public static void rest() {
		RestTemplate template = new RestTemplate();
		String json = "{\r\n" + 
				"\r\n" + 
				"    \"touser\":\"oY-X11CvfoznTLUMkVCuiCXIL1ec\",\r\n" + 
				"    \"template_id\":\"eJJkLXzx-v_iHS1kBwb9fBUTqsCaL7JlcvFXBF3UxQY\",\r\n" + 
				"    \"topcolor\":\"#FF0000\",\r\n" + 
				"    \"data\":{\r\n" + 
				"            \"user\": {\r\n" + 
				"                \"value\":\"每天一个仔\",\r\n" + 
				"                \"color\":\"#173177\"\r\n" + 
				"\r\n" + 
				"            },\r\n" + 
				"             \"code\": {\r\n" + 
				"                \"value\":\"0406\",\r\n" + 
				"                \"color\":\"#173177\"\r\n" + 
				"\r\n" + 
				"            },\r\n" + 
				"             \"time\": {\r\n" + 
				"                \"value\":\"2小时\",\r\n" + 
				"                \"color\":\"#173177\"\r\n" + 
				"\r\n" + 
				"            }\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"}";
		String msg = template.postForObject("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=20_PMk-err580KD6vwJwt_aP7yxsvtlsJL4Mf-Nv8Hzh7KFeLHDuDdshfAR84o1CN6gcTj5EtNqP-st2DL4pqgMhebed8vvGUhq7X-dJHc-0Z7ixa34Mu5ow5DOiX8VKUeAFACRB",
				json, String.class);
		System.out.println(msg);
	}
	
	public void okHttpMessage() {
		MediaType JSON = MediaType.get("application/json; charset=utf-8");

		OkHttpClient client = new OkHttpClient();
		String json = "{\r\n" + 
				"\r\n" + 
				"    \"touser\":\"oY-X11CvfoznTLUMkVCuiCXIL1ec\",\r\n" + 
				"    \"template_id\":\"eJJkLXzx-v_iHS1kBwb9fBUTqsCaL7JlcvFXBF3UxQY\",\r\n" + 
				"    \"topcolor\":\"#FF0000\",\r\n" + 
				"    \"data\":{\r\n" + 
				"            \"user\": {\r\n" + 
				"                \"value\":\"每天一个仔\",\r\n" + 
				"                \"color\":\"#173177\"\r\n" + 
				"\r\n" + 
				"            },\r\n" + 
				"             \"code\": {\r\n" + 
				"                \"value\":\"0406\",\r\n" + 
				"                \"color\":\"#173177\"\r\n" + 
				"\r\n" + 
				"            },\r\n" + 
				"             \"time\": {\r\n" + 
				"                \"value\":\"2小时\",\r\n" + 
				"                \"color\":\"#173177\"\r\n" + 
				"\r\n" + 
				"            }\r\n" + 
				"    }\r\n" + 
				"\r\n" + 
				"}";
		RequestBody body = RequestBody.create(JSON, json);
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=20_PMk-err580KD6vwJwt_aP7yxsvtlsJL4Mf-Nv8Hzh7KFeLHDuDdshfAR84o1CN6gcTj5EtNqP-st2DL4pqgMhebed8vvGUhq7X-dJHc-0Z7ixa34Mu5ow5DOiX8VKUeAFACRB";
		Request request = new Request.Builder().url(url).post(body).build();

		Response response;
		try {
			// 发送请求
			response = client.newCall(request).execute();
			ResponseBody body2 = response.body();
			String msg = new String(body2.bytes(), "utf-8");
			System.out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/
	
}
