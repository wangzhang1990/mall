package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult query(String queryString, Integer page);

}
