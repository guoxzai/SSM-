package com.gxz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.gxz.aspect.LogAspect;
import com.gxz.config.AlipayConfig;
import com.gxz.model.Pay;
import com.gxz.service.AliPayService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Service
public class AliPayServiceImpl implements AliPayService {
	
	private static Logger log = LoggerFactory.getLogger(LogAspect.class);

	// 支付宝当面付2.0服务
	private static AlipayTradeService tradeService;
	
	@Value("http://www.baidu.com")
	private String nofityUrl;
	 
	static {
	        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
	         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
	         */
	        Configs.init("zfbinfo.properties");
	
	        /** 使用Configs提供的默认参数
	         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
	         */
	        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
	
	 }
	 
	@Override
	public String pay(Pay pay) {
		String result = null;
		
		if(null == pay) return null;
		
		log.info("开始支付");
		
		switch (pay.getPayType()) {
			case 1:
				log.info("扫描支付");
				result =getScanPayResult(pay);
				break ;
			case 2:
				log.info("电脑支付");
				result = getComputerPayResult(pay);
				break ;
			default :
				throw new RuntimeException("不支持的方式");			
		}
		
		
		return result;
	}

	private String getComputerPayResult(Pay pay) {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		String data = JSON.toJSONString(pay);
		
		AlipayTradePagePayModel alipayTradePagePayModel = new AlipayTradePagePayModel();
		alipayTradePagePayModel.setOutTradeNo(pay.getOutTradeNo());
		alipayTradePagePayModel.setSubject(pay.getSubject());
		alipayTradePagePayModel.setTotalAmount(pay.getTotalAmount()+"");
		alipayTradePagePayModel.setBody(pay.getBody());
		alipayTradePagePayModel.setProductCode(pay.getProductCode());
		alipayTradePagePayModel.setTimeoutExpress(pay.getTimeoutExpress());
		alipayRequest.setBizModel(alipayTradePagePayModel);
		
		//请求
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}


	private String getScanPayResult(Pay pay) {
		  // 创建扫码支付请求builder，设置请求参数
		AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder();
		builder.
		setOutTradeNo(pay.getOutTradeNo())
		.setSubject(pay.getSubject())
		.setBody(pay.getBody())
		.setTotalAmount(pay.getTotalAmount())
		.setTimeoutExpress(pay.getTimeoutExpress())
		.setStoreId(pay.getStoreId())
		.setNotifyUrl(nofityUrl);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                return response.getQrCode();

            case FAILED:
                log.error("支付宝预下单失败!!!");
                return null;
                
            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                return null;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                return null;
        }
	}
	
	 // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                    response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

}
