package com.gxz.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gxz.model.OrderVo;
import com.gxz.model.Pay;
import com.gxz.service.AliPayService;
import com.gxz.service.OrderService;

@Controller
public class PayController {
	
	@Reference
	private AliPayService aliPayService;

	@Reference
	private OrderService orderService;
	
	@RequestMapping("/toPay")
	public String toPay(@RequestParam(required=true)String orderSn,Model model) {
		OrderVo orderVo = orderService.queryOrderByOrderSn(orderSn);
		return "pay";
	}
	
	@RequestMapping("/doPay")
	public void doPay(HttpServletResponse response,Pay pay) {
		String result = aliPayService.pay(pay);
		if(pay.getPayType()==1) { // 扫描支付
			// 给用户展示二维码
			try {
				response.getWriter().print(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(pay.getPayType()==2){ // 电脑支付
			try {
//				String htmPre="<!DOCTYPE html>\r\n" + 
//						"<html>\r\n" + 
//						"<body>";
//				
//				String htmPost="</body>\r\n" + 
//						"</html>";
//				response.getWriter().print(htmPre+result+htmPost);
				//Ajax  解决乱码    后台不能解决乱码
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter pw = response.getWriter();
				pw.write(result);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
