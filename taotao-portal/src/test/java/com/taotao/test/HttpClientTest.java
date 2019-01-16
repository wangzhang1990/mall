package com.taotao.test;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	
	@Test
	public void doGetWithParam() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		
		URIBuilder uriBuilder = new URIBuilder("http://localhost:8081/rest/itemCat/list");
		uriBuilder.addParameter("callback", "hello");
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		
		CloseableHttpResponse response = client.execute(httpGet);
		
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		response.close();
		client.close();
	}
	
	@Test
	public void doGet() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8081/rest/itemCat/list?callback=what");
		CloseableHttpResponse response = client.execute(httpGet);
		
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		response.close();
		client.close();
	}
	
	@Test
	public void doPost() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		//HttpGet httpGet = new HttpGet("http://localhost:8081/rest/itemCat/list");
		HttpPost httpPost = new HttpPost("http://localhost:8081/rest/content/list/89");
		CloseableHttpResponse response = client.execute(httpPost);
		
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		response.close();
		client.close();
	}
}
