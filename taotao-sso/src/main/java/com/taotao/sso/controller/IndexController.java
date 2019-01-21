package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class IndexController {
	
	@RequestMapping("/login")
	public String loginIndex(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping("/register")
	public String registerIndex() {
		return "register";
	}
}
