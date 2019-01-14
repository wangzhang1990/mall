package com.taotao.service;

import java.util.List;

import com.taotao.pojo.TbContentCategory;
import com.taotao.result.TaotaoResult;
import com.taotao.result.TreeNode;

public interface ContentCatSerevice {

	List<TreeNode> getContentCatList(long parentId);

	TaotaoResult addCategory(TbContentCategory contentCategory);

}
