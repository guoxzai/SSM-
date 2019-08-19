package com.gxz.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResutl implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private long expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
}
