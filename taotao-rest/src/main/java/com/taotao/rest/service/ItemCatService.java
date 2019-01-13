package com.taotao.rest.service;

import java.util.List;

import com.taotao.rest.pojo.CatResult;

public interface ItemCatService {

	CatResult getItemCatList();

	List<?> getCatList(long parentId);

}
