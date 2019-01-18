package com.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	private SearchDao searchDao;

	@Override
	public SearchResult search(String queryString, Integer page, Integer rows) throws SolrServerException {
		// TODO Auto-generated method stub
		SolrQuery query = new SolrQuery();
		query.add("q", queryString);
		query.setStart((page - 1) * rows);
		query.setRows(rows);
		query.set("df", "item_keywords");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		
		SearchResult searchResult = searchDao.search(query);
		searchResult.setCurPage(page);
		
		long recordCount = searchResult.getRecordCount();
		long pageCount = recordCount / rows;
		if (recordCount % rows != 0) {
			pageCount = pageCount + 1;
		}
		searchResult.setPageCount(pageCount);
		return searchResult;
	}

}
