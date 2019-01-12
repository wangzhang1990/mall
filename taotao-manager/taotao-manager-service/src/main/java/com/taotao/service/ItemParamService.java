package com.taotao.service;

import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;

public interface ItemParamService {

	EUDataGridResult getItemParamList(int page, int rows);

	TaotaoResult getItemParamByCid(Long cid);

	TaotaoResult insertItemParam(Long cid, String paramData);

}
