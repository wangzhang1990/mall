package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;

@Controller
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EUDataGridResult getItemList(int page, int rows) {
		
		return itemParamService.getItemParamList(page, rows);
	}
	
	@RequestMapping("/item/param/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long cid) {
		return itemParamService.getItemParamByCid(cid);
	}
	
	@RequestMapping("/item/param/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
		return itemParamService.insertItemParam(cid, paramData);
	}
}
