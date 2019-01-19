package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.service.ItemService;
import com.taotao.result.TaotaoResult;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{itemId}")
	public String getItemBaseInfo(@PathVariable Long itemId, Model model) {
		TaotaoResult taotaoResult = itemService.getItemBaseInfo(itemId);
		model.addAttribute("item", taotaoResult.getData());
		return "item";
	}
	
	@RequestMapping(value = "/desc/{itemId}", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId) {
		TaotaoResult taotaoResult = itemService.getItemDesc(itemId);
		TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
		return itemDesc.getItemDesc();
	}
	
	@RequestMapping(value = "/param/{itemId}", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId) {
		String data = itemService.getItemParam(itemId);
		
		
		return data;
	}
	
	@RequestMapping(value = "/par", produces = "application/json; charset=utf-8")
	@ResponseBody
	public TaotaoResult getTest() {
		
		return TaotaoResult.ok("hello world");
	}
	
}
