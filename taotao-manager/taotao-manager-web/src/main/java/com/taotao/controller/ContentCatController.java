package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbContentCategory;
import com.taotao.result.TaotaoResult;
import com.taotao.result.TreeNode;
import com.taotao.service.ContentCatSerevice;

@Controller
@RequestMapping("/content/category/")
public class ContentCatController {
	@Autowired
	private ContentCatSerevice contentCatService;
	@RequestMapping("/list")
	@ResponseBody
	public List<TreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		return contentCatService.getContentCatList(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createCat(TbContentCategory contentCategory) {
		return contentCatService.addCategory(contentCategory);
	}
	
	
}
