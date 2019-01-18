package com.taotao.search.service;

import com.taotao.result.TaotaoResult;

public interface ItemService {
	/*
	 * 将所有的item数据导入solr索引库
	 */
	TaotaoResult importAll();
}
