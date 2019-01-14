package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.result.TaotaoResult;
import com.taotao.result.TreeNode;
import com.taotao.service.ContentCatSerevice;

@Service
public class ContentCatSereviceImpl implements ContentCatSerevice {
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<TreeNode> getContentCatList(long parentId) {
		// TODO Auto-generated method stub
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		
		List<TreeNode> nodeList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			TreeNode treeNode = new TreeNode(tbContentCategory.getId(), tbContentCategory.getName(), tbContentCategory.getIsParent() ? "closed" : "open");
			nodeList.add(treeNode);
		}
		return nodeList;
	}

	@Override
	public TaotaoResult addCategory(TbContentCategory contentCategory) {
		// TODO Auto-generated method stub
		try {
			Date date = new Date();
			contentCategory.setCreated(date);
			contentCategory.setUpdated(date);
			contentCategory.setSortOrder(1);
			contentCategory.setStatus(1);
			contentCategory.setIsParent(false);
			tbContentCategoryMapper.insert(contentCategory);
			
			TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
			if (!parent.getIsParent()) {
				parent.setIsParent(true);
				tbContentCategoryMapper.updateByPrimaryKey(parent);
			}
			
			TaotaoResult ok = TaotaoResult.ok(contentCategory);
			return ok;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TaotaoResult build = TaotaoResult.build(500, e.getMessage());
			return build;
		}
	}

}
