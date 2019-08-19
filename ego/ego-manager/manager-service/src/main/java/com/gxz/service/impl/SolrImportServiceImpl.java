package com.gxz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.gxz.domain.Goods;
import com.gxz.domain.GoodsExample;
import com.gxz.mapper.GoodsMapper;
import com.gxz.service.GoodsTypeService;
import com.gxz.service.SolrImportService;

@Service(timeout=7000)
public class SolrImportServiceImpl implements SolrImportService {
	
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private SolrClient solrClient;
	@Autowired
	private GoodsTypeService goodsTypeService;
	
	
	private Integer rows = 200;
	
	/**
	 * 手动导入
	 */
	@Override
	public long importGoodsDataForSolr() {
		//前台类别合并搜索   公用模糊查询        查询的合并
		Map<Integer, String> goodsTypeMap = goodsTypeService.selecAllMap();
		
		//得到共有多少条数据
		long total = goodsMapper.countByExample(null);
		int totalPage = (int)(total%rows==0?total/rows:(total/rows)+1);
		for(int i = 1 ;i <= totalPage;i++) {
			PageHelper.startPage(i, rows);
			List<Goods> goods = goodsMapper.selectByExample(null);
			//导入 分页
			List<SolrInputDocument> docs = new ArrayList<>();
			for (Goods good : goods) {
				docs.add(goodsToDoc(good,goodsTypeMap));
			}
			
			try {
				// 导入一个集合进去
				solrClient.add(docs);
				// 每一页提交一次
				solrClient.commit();
				System.out.println("第"+i+"次导入成功");
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("第"+i+"次导入失败");
			}
		}
		
		return total;
	}
	
	// 开始记录的时间，类似t1 1970
	private Date start = new Date(0);
	
	/**
	 * 使用增量的导入过程
	 * @Scheduled ： 则需要开启spring-task的注解开发开发
	 */
	@Scheduled(fixedRate=20*1000) //ms为单位  1 min 该方法执行一次    t1 需要自己记录 t2 = t1+1min
	@Override
	public long AutoimportGoodsDataForSolr() {
		System.out.println("自动导入数据开始");
		//前台类别合并搜索   公用模糊查询        查询的合并
		Map<Integer, String> goodsTypeMap = goodsTypeService.selecAllMap();
		
		Date end = new Date(System.currentTimeMillis());
		GoodsExample example = new GoodsExample();
		example.createCriteria().andUpdatetimeBetween(start, end);
		long total = goodsMapper.countByExample(example);
		
		long totalPage = total%rows==0?total/rows:(total/rows+1);
		for(int i = 1 ; i <= totalPage ;i++) {
			PageHelper.startPage(i, rows);
			
			List<Goods> goods = goodsMapper.selectByExample(example);
			List<SolrInputDocument> docs = new ArrayList<>();
			for (Goods gd : goods) {
				SolrInputDocument doc = goodsToDoc(gd,goodsTypeMap);
				docs.add(doc);
			}
			
			try {
				solrClient.add(docs);
				solrClient.commit();
				System.out.println("第"+i+"次导入成功");
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("第"+i+"次导入失败");
			}
			
		}
		
		start = end; // 将t2 赋值给t1 ，时间更新
		
		return total;
	}
	
	private SolrInputDocument goodsToDoc(Goods good,Map<Integer,String> goodsTypeMap) {
		SolrInputDocument solrInputDocument = new SolrInputDocument();
		solrInputDocument.setField("id", good.getId().toString());
		solrInputDocument.setField("goods_name", good.getGoodsName());
		solrInputDocument.setField("goods_price", good.getShopPrice()==null?0.0:good.getShopPrice().doubleValue());
		solrInputDocument.setField("goods_img", good.getOriginalImg());
		solrInputDocument.setField("goods_comment_count", good.getCommentCount()==null?0L:good.getCommentCount().longValue());
		
		//前台类别合并搜索   公用模糊查询        查询的合并
		solrInputDocument.setField("goods_type_name", goodsTypeMap.get(good.getCatId()));
		return solrInputDocument;
	}

}
