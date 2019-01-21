package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.result.TaotaoResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		TaotaoResult taotaoResult = cartService.addCartItem(itemId, num, request, response);
		
		return "cartSuccess";
	}
	@RequestMapping("/cart")
	public String showCart(HttpServletRequest request, Model model) {
		List<CartItem> cartItemList = cartService.getCartItemList(request);
		model.addAttribute("cartList", cartItemList);
		return "cart";
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCatItem(@PathVariable Long itemId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<CartItem> cartItemList = cartService.deleteCartItem(itemId, request, response);
		model.addAttribute("cartList", cartItemList);
		return "cart";
	}
	
	
}
