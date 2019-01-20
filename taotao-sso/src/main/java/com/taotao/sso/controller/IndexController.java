package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/loginIndex")
	public String loginIndex() {
		return "login";
	}
	
	@RequestMapping("/registerIndex")
	public String registerIndex() {
		return "register";
	}
}
