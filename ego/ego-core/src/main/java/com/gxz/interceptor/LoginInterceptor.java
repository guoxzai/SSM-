package com.gxz.interceptor;

import com.gxz.redis.RedisClient;
import com.gxz.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisClient redisClient;
	@Value("http://sso.ego.com:8082/toLogin")
	private String ssoUrl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = CookieUtil.getCookieValue(request, "X-TOKEN");
		if(null != token || redisClient.isExist(token)) {
			return true;
		}else {
			StringBuffer requestURL = request.getRequestURL();
			response.sendRedirect(ssoUrl+"?lastUrl="+requestURL);
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
