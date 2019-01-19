package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.service.ItemService;
import com.taotao.result.TaotaoResult;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{id}")
	@ResponseBody
	public TaotaoResult getItem(@PathVariable Long id) {
		TaotaoResult taotaoResult = itemService.getItem(id);
		return taotaoResult;
	}
	
	@RequestMapping("/desc/{id}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long id) {
		TaotaoResult taotaoResult = itemService.getItemDesc(id);
		return taotaoResult;
	}
	
	@RequestMapping("/param/{id}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable Long id) {
		TaotaoResult taotaoResult = itemService.getItemParam(id);
		return taotaoResult;
	}
	
	
}
