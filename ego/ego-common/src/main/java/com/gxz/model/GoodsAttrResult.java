package com.gxz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;


/*{
	"status": 200,
	"data": {
		"paramData": [
			{
				"group": "内存",
				"params": [
					"1、4G",
					"2、8G",
					"1、16G",
					"1、32G"
				]
			},
			{
				"group": "g2",
				"params": [
					"g2-1",
					"g2-2"
				]
			}
		]
	}
}*/
public class GoodsAttrResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer status;
	private Map<String, List<GoodsAttrGroup>> data;

	public GoodsAttrResult() {}
	
	public GoodsAttrResult(Integer status, Map<String, List<GoodsAttrGroup>> data) {
		super();
		this.status = status;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Map<String, List<GoodsAttrGroup>> getData() {
		return data;
	}

	public void setData(Map<String, List<GoodsAttrGroup>> data) {
		this.data = data;
	}

	public static void main(String[] args) {
		GoodsAttrResult egoItemAttrResult = new GoodsAttrResult();
		egoItemAttrResult.setStatus(200);
		Map<String,List<GoodsAttrGroup>> datas = new HashMap<>();
		List<GoodsAttrGroup> gruops = new ArrayList<>();
		GoodsAttrGroup group1 = new GoodsAttrGroup(1,"group1", Arrays.asList("g1-1","g1-2"));
		gruops.add(group1 );
		GoodsAttrGroup group2 = new GoodsAttrGroup(2,"group2", Arrays.asList("g2-1","g2-2"));
		gruops.add(group2 );
		datas.put("paramData", gruops );
		egoItemAttrResult.setData(datas);
		String jsonString = JSON.toJSONString(egoItemAttrResult);
		System.out.println(jsonString);
	}

}
