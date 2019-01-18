package com.taotao.search.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrjTest {
	@Test
	public void solrjDemo1() throws SolrServerException, IOException {
		HttpSolrServer server = new HttpSolrServer("http://192.168.194.133:8080/solr");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test002");
		document.addField("item_title", "测试商品标题2");
		document.addField("item_price", 50035);
		server.add(document);
		server.commit();
	}
	
	@Test
	public void query() throws SolrServerException, IOException {
		HttpSolrServer server = new HttpSolrServer("http://192.168.194.133:8080/solr");
		SolrQuery query = new SolrQuery();
		query.add("q", "id:816448");
		QueryResponse response = server.query(query);
		SolrDocumentList results = response.getResults();
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("item_title"));
		}
	}
	
	
	public void delete() throws SolrServerException, IOException {
		HttpSolrServer server = new HttpSolrServer("http://192.168.194.133:8080/solr");
		//server.deleteById("test002");
		server.deleteByQuery("* : *");
		server.commit();
	}
}
