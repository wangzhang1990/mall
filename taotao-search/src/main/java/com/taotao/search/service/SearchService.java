package com.taotao.search.service;

import org.apache.solr.client.solrj.SolrServerException;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, Integer page, Integer rows) throws SolrServerException;

}
