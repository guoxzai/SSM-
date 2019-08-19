package com.gxz.service;

import com.gxz.model.WeChatMessage;

public interface WeChatMessageService {

	/**
	 * 发送微信消息
	 */
	void sendMessage(WeChatMessage wechatMessage);
}
