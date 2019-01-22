package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import com.taotao.result.TaotaoResult;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult orderTest(@RequestBody Order order) {
		try {
			TaotaoResult taotaoResult = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return taotaoResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}
}
