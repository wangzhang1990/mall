package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.result.TaotaoResult;

public interface CartService {

	TaotaoResult addCartItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);

	List<CartItem> getCartItemList(HttpServletRequest request);

	List<CartItem> deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response);

}
