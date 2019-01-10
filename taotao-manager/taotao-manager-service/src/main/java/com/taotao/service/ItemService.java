package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;

public interface ItemService {
	//根据id查询
	TbItem getItemById(long itemId);
	
	//商品列表查询，分页
	EUDataGridResult getItemList(int page, int rows);

	TaotaoResult addItem(TbItem item, TbItemDesc itemDesc);
	
}
