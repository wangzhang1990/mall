package com.taotao.portal.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import com.taotao.result.TaotaoResult;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;
@Service
public class SearchServiceImpl implements SearchService {
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult query(String queryString, Integer page) {
		// TODO Auto-generated method stub
		HashMap<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		param.put("rows", 30 + "");
		
		try {
			String result = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
			
			if (taotaoResult.getStatus() == 200) {
				return (SearchResult) taotaoResult.getData();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
