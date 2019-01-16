package com.taotao.rest.service;

import java.util.List;

import com.taotao.pojo.TbContent;
import com.taotao.result.TaotaoResult;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);

}
