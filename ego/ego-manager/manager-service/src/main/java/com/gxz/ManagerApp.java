package com.gxz;

import java.io.IOException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gxz.domain.Goods;
import com.gxz.mapper.GoodsMapper;
import com.gxz.model.PageBean;
import com.gxz.service.GoodsService;
import com.gxz.service.SolrImportService;

public class ManagerApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SolrImportService bean = ac.getBean(SolrImportService.class);
//		bean.importGoodsDataForSolr();
		
		try {
			System.out.println("ManagerApp 服务注册成功");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
