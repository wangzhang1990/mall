package com.taotao.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.result.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.pojo.Item;
import com.taotao.search.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private HttpSolrServer httpSolrServer;

	@Override
	public TaotaoResult importAll() {
		// TODO Auto-generated method stub
		try {
		List<Item> itemList = itemMapper.getItemList();
		for (Item item : itemList) {
			SolrInputDocument document = new SolrInputDocument();
			document.setField("id", item.getId());
			document.setField("item_title", item.getTitle());
			document.setField("item_sell_point", item.getSell_point());
			document.setField("item_price", item.getPrice());
			document.setField("item_image", item.getImage());
			document.setField("item_category_name", item.getCategory_name());
			document.setField("item_desc", item.getItem_des());
			httpSolrServer.add(document);
		}
			httpSolrServer.commit();
			return TaotaoResult.ok();
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}

}
