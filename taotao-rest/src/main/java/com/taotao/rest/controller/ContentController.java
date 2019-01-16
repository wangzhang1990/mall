package com.taotao.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbContent;
import com.taotao.rest.service.ContentService;
import com.taotao.result.TaotaoResult;

@Controller
@RequestMapping("/content/")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value = "/list/{contentCid}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable long contentCid) {
		try {
			List<TbContent> list = contentService.getContentList(contentCid);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}
}
