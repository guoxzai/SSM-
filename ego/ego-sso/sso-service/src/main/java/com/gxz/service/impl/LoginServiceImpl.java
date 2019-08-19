package com.gxz.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.gxz.domain.Admin;
import com.gxz.domain.AdminExample;
import com.gxz.domain.AdminWithBLOBs;
import com.gxz.mapper.AdminMapper;
import com.gxz.redis.RedisClient;
import com.gxz.service.LoginService;
import com.gxz.utils.Md5Util;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private RedisClient redisClient;
	
	@Override
	public String doLogin(String username, String password) {
		String token = null;
		
		AdminExample example = new AdminExample();
		example.createCriteria().andUserNameEqualTo(username);
		List<Admin> admins = adminMapper.selectByExample(example);
		if(null == admins) {
			throw new RuntimeException("用户不存在需要注册");
		}
		
		if(admins.size()>1) {
			throw new RuntimeException("数据库异常");
		}
		
		Admin admin = admins.get(0);
		String adminSalt = admin.getEcSalt();
		String pwd = Md5Util.GetMD5WithSalt(password, adminSalt);
		if(null != admin && !pwd.equals(admin.getPassword())) {
			throw new RuntimeException("用户密码错误");
		}
		
		token = UUID.randomUUID().toString();
		admin.setEcSalt("******");
		admin.setPassword("******");
		redisClient.set(token, JSON.toJSONString(admin));
		redisClient.setExpire(token, 1*24*3600);//设置一天过期
		return token;
	}

	@Override
	public int addUser(String username, String password) {
		AdminWithBLOBs record = new AdminWithBLOBs();
		record.setUserName(username);
		record.setEcSalt("ego_register_salt");
		record.setPassword(Md5Util.GetMD5WithSalt(password, "ego_register_salt"));
		return adminMapper.insert(record);
	}

}
