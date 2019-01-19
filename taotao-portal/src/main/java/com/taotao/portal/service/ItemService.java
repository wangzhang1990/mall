package com.taotao.portal.service;

import com.taotao.result.TaotaoResult;

public interface ItemService {

	TaotaoResult getItemBaseInfo(Long itemId);

	TaotaoResult getItemDesc(Long itemId);

	String getItemParam(Long itemId);

}
