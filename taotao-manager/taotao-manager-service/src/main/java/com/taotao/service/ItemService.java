package com.taotao.service;

import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	//根据id查询
	TbItem getItemById(long itemId);
	
	//商品列表查询，分页
	EUDataGridResult getItemList(int page, int rows);
	
}
