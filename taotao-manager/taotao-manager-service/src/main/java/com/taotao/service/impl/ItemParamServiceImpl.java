package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.result.EUDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		// TODO Auto-generated method stub
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.isEmpty()) {
			TaotaoResult result = TaotaoResult.build(500, "查无此数据");
			return result;
		}
		TaotaoResult result = TaotaoResult.ok(list.get(0));
		return result;
	}

	@Override
	public TaotaoResult insertItemParam(Long cid, String paramData) {
		// TODO Auto-generated method stub
		try {
			TbItemParam itemParam = new TbItemParam();
			itemParam.setItemCatId(cid);
			itemParam.setParamData(paramData);
			Date date = new Date();
			itemParam.setCreated(date);
			itemParam.setUpdated(date);
			tbItemParamMapper.insert(itemParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
		
		return TaotaoResult.ok();
	}

}
