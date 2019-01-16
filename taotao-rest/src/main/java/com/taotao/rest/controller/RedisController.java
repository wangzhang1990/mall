package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.rest.service.ContentService;
import com.taotao.rest.service.RedisService;
import com.taotao.result.TaotaoResult;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	@Autowired
	private RedisService redisService;
	
	@RequestMapping(value = "/content/{contentCid}")
	public TaotaoResult contentCacheSync(@PathVariable long contentCid) {
		return redisService.sync(contentCid);
	}
}
