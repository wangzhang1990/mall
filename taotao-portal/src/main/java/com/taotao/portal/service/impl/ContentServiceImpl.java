package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;
import com.taotao.result.TaotaoResult;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class ContentServiceImpl implements ContentService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;

	@Override
	public String getContentList() {
		// TODO Auto-generated method stub
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
		List<Map> adResult = new ArrayList<>();
		for (TbContent content : (List<TbContent>) taotaoResult.getData()) {
			HashMap map = new HashMap<>();
			map.put("src", content.getPic());
			map.put("height", 240);
			map.put("width", 670);
			map.put("srcB", content.getPic2());
			map.put("widthB", 550);
			map.put("heightB", 240);
			map.put("href", content.getUrl());
			map.put("alt", content.getSubTitle());
			adResult.add(map);
		}
		return JsonUtils.objectToJson(adResult);
	}

}
