package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		CatResult result = new CatResult();
		result.setData(getCatList(0));
		return result;
	}
	
	@Override
	public List<?> getCatList(long parentId) {
		// TODO Auto-generated method stub
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		//获取到最顶层的一层数据
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		
		//创建返回值的list
		List resultList = new ArrayList<>();
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			CatNode node = new CatNode();
			Long id = tbItemCat.getId();
			
			if (tbItemCat.getIsParent()) {
				if (tbItemCat.getParentId() == 0L) {
					node.setUrl("/products/" + id + ".html");
					node.setName("<a href='/products/" + id + ".html'>" + tbItemCat.getName() +"</a>");
					node.setItem(getCatList(id));
					resultList.add(node);
					count ++;
					if (count >= 14) {
						break;
					}
				}
				
				if (tbItemCat.getParentId() != 0L) {
					node.setUrl("/products/" + id + ".html");
					node.setName(tbItemCat.getName());
					node.setItem(getCatList(id));
					resultList.add(node);
				}
			} else {
				resultList.add("/products/" + id + ".html|" + tbItemCat.getName());
			}
			
		}
		
		return resultList;
	}
}
