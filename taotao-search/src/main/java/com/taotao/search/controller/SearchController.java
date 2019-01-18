package com.taotao.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/query")
	@ResponseBody
	public TaotaoResult search(@RequestParam(value = "q", defaultValue = "") String queryString, 
			@RequestParam(defaultValue = "1") Integer page, 
			@RequestParam(defaultValue = "60") Integer rows) {
		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件不能为空");
		}
		
		try {
			queryString = new String(queryString.getBytes("iso-8859-1"), "utf-8");
			SearchResult result = searchService.search(queryString, page, rows);
			return TaotaoResult.ok(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		
	}
}
