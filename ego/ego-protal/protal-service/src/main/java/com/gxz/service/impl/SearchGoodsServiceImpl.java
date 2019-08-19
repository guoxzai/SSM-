package com.gxz.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.gxz.domain.Goods;
import com.gxz.model.PageBean;
import com.gxz.model.SearchGoodsResult;
import com.gxz.model.SearchGoodsVo;
import com.gxz.service.GoodsService;
import com.gxz.service.SearchGoodsService;

@Service(timeout=7000)
public class SearchGoodsServiceImpl implements SearchGoodsService {

	@Autowired
	private HttpSolrClient solr;
	@Reference   //即是服务的提供者   又是服务的消费者
	private GoodsService goodsService;
	
	@Override
	public SearchGoodsResult doSearchGoods(SearchGoodsVo searchVo,boolean price,boolean comment,Integer min,Integer max) {
		SearchGoodsResult searchGoodsResult = new SearchGoodsResult();
		
		String keywords = searchVo.getKeywords();
		Integer currentPage = searchVo.getCurrentPage();
		Integer pageSize = searchVo.getPageSize();
		
		if(null==keywords && "".equals(keywords)) {
			return null;
		}

//		SolrQuery solrQuery = new SolrQuery("goods_name:"+keywords);
		SolrQuery solrQuery = new SolrQuery("keywords:"+keywords);
		
		// 设置区间查询
		if(min != null && max != null) {
			solrQuery.setFilterQueries("goods_price:["+min+" TO "+max+"]","id:[1 TO 150]");
		}
		
		solrQuery.setStart((currentPage-1)*pageSize);
		solrQuery.setRows(pageSize);
		
		// 价格排序
		if(price) {
			solrQuery.addSort("goods_price", ORDER.asc);
		}
		
		// 评论数排序
		if(comment) {
			solrQuery.addSort("goods_comment_count", ORDER.desc);
		}
		
		//高亮操作
		solrQuery.setHighlight(true);
		solrQuery.setHighlightSimplePre("<font color='red'>");
		solrQuery.setHighlightSimplePost("</font>");
		solrQuery.addHighlightField("goods_name");
		
		try {
			QueryResponse queryResponse = solr.query(solrQuery);
			SolrDocumentList result = queryResponse.getResults();
			
			Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
			
			//将solr中的字段转换到前台
			List<SearchGoodsVo> searchGoodsVoList = new  ArrayList<>();
			
			for (SolrDocument solrDocument : result) {
				SearchGoodsVo goodsVo = docToGoodsVo(solrDocument);
				
				// 在使用goods_type_name 时无法使用 keywords  = goods_name + goods_type_name
				//String goodsNameHl = map.get(goodsVo.getId()).get("goods_name").get(0);	
				String goodsNameHl = null;
				if(null != map.get(goodsVo.getId()).get("goods_name")) {
					if(null != map && map.get(goodsVo.getId()).get("goods_name").size()>0) {
						goodsNameHl = map.get(goodsVo.getId()).get("goods_name").get(0);
					}
					
				}else {
					goodsNameHl = solrDocument.getFieldValue("goods_name").toString();
				}
				goodsVo.setGoodsNameHl(goodsNameHl);
				
				searchGoodsVoList.add(goodsVo);
			}
			
			searchGoodsResult.setPage(pageSize);
			searchGoodsResult.setTotal(result.getNumFound());
			searchGoodsResult.setResults(searchGoodsVoList);
			
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			// 若solr 搜索失败，则进入该方法    统计失败的次数
			throw new RuntimeException("solr调用使用"); // 若不抛，则异常已经被处理完了，不会留给切面
		}
		
		
		return searchGoodsResult;
	}
	
	private SearchGoodsVo docToGoodsVo(SolrDocument solrDocument) {
		SearchGoodsVo searchGoodsVo = new SearchGoodsVo();
		searchGoodsVo.setId(solrDocument.getFieldValue("id").toString());
		searchGoodsVo.setGoodsImg(solrDocument.getFieldValue("goods_img").toString());
		searchGoodsVo.setGoodsPrice(solrDocument.getFieldValue("goods_price").toString());
		
		return searchGoodsVo;
	}
	
	/**
	 *前台根据种类列表  查询商品
	 */
	@Override
	public SearchGoodsResult doQuery(Integer catId, int page, int rows, boolean price, boolean comment,Integer min, Integer max) {
	
		PageBean<Goods> goodsPage = goodsService.queryGoods(catId,page,rows,price,comment,min,max);
		
		SearchGoodsResult searchGoodsResult = new SearchGoodsResult();
		searchGoodsResult.setPage(page);
		searchGoodsResult.setTotal(goodsPage.getTotal());
		List<Goods> data = goodsPage.getData();
		List<SearchGoodsVo> goodsVos = new ArrayList<SearchGoodsVo>();
		data.forEach((goods)->{
			goodsVos.add(goods2SearchGoodsVo(goods));
		});
		searchGoodsResult.setResults(goodsVos);
		
		return searchGoodsResult;
	}

	private SearchGoodsVo goods2SearchGoodsVo(Goods goods) {
		SearchGoodsVo searchGoodsVo = new SearchGoodsVo();
		searchGoodsVo.setId(goods.getId().toString());
		searchGoodsVo.setGoodsNameHl(goods.getGoodsName());
		searchGoodsVo.setGoodsPrice(goods.getShopPrice().toString());
		searchGoodsVo.setGoodsImg(goods.getOriginalImg());
		return searchGoodsVo;
	}

	

}
