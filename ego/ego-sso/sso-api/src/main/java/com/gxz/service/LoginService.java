package com.gxz.service;

public interface LoginService {

	String doLogin(String username, String password);

	int addUser(String username, String password);
	
}
