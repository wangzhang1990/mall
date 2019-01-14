package com.taotao.service;

import com.taotao.pojo.TbContent;
import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;

public interface ContentService {


	EUDataGridResult getContentList(Long categoryId, int page, int rows);

	TaotaoResult addContent(TbContent content);

}
