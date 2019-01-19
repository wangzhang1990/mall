package com.taotao.rest.service;

import com.taotao.result.TaotaoResult;

public interface ItemService {

	TaotaoResult getItem(Long id);

	TaotaoResult getItemDesc(Long id);

	TaotaoResult getItemParam(Long id);

}
