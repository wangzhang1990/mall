package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.result.TaotaoResult;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class CartServiceImpl implements CartService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_URL}")
	private String REST_ITEM_URL;

	@Override
	public TaotaoResult addCartItem(Long itemId, Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub\
		CartItem cartItemNew = null;
		List<CartItem> cartItemList = getCartItemList(request);
		if (cartItemList == null) {
			cartItemList = new ArrayList<CartItem>();
		}
		
		for (CartItem cartItem : cartItemList) {
			if (cartItem.getId() == itemId) {
				cartItem.setNum(cartItem.getNum() + num);
				cartItemNew = cartItem;
				break;
			}
		}

		if (cartItemNew == null) {
			cartItemNew = new CartItem();
			String doGet = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_URL + itemId);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(doGet, TbItem.class);
			if (taotaoResult.getStatus() == 200) {
				TbItem item = (TbItem) taotaoResult.getData();
				cartItemNew.setId(item.getId());
				cartItemNew.setTitle(item.getTitle());
				cartItemNew.setImage(item.getImage() == null ? "":item.getImage().split(",")[0]);
				cartItemNew.setNum(num);
				cartItemNew.setPrice(item.getPrice());
				cartItemList.add(cartItemNew);
			}
			
		}
		//没有设置最后一个参数为true，随后看
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItemList));
		return TaotaoResult.ok();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String cookieValue = CookieUtils.getCookieValue(request, "TT_CART");
		if (cookieValue == null) {
			return null;
		}
		try {
			List<CartItem> list = JsonUtils.jsonToList(cookieValue, CartItem.class);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<CartItem> deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		List<CartItem> cartItemList = getCartItemList(request);
		
		for (CartItem cartItem : cartItemList) {
			if (cartItem.getId() == itemId) {
				cartItemList.remove(cartItem);
				break;
			}
		}
		
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartItemList));
		
		return cartItemList;
	}

}
