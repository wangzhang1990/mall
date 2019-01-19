package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;
import com.taotao.result.TaotaoResult;
import com.taotao.utils.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;

	@Override
	public TaotaoResult getItem(Long id) {
		// TODO Auto-generated method stub
		String key = REDIS_ITEM_KEY + ":" + id + ":base";

		String jedisResult = jedisClient.get(key);
		if (!StringUtils.isBlank(jedisResult)) {
			TbItem item = JsonUtils.jsonToPojo(jedisResult, TbItem.class);
			return TaotaoResult.ok(item);
		}

		TbItem item = itemMapper.selectByPrimaryKey(id);

		try {
			jedisClient.set(key, JsonUtils.objectToJson(item));
			jedisClient.expire(key, REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDesc(Long id) {
		String key = REDIS_ITEM_KEY + ":" + id + ":desc";

		String jedisResult = jedisClient.get(key);
		if (!StringUtils.isBlank(jedisResult)) {
			TbItemDesc itemDesc = JsonUtils.jsonToPojo(jedisResult, TbItemDesc.class);
			return TaotaoResult.ok(itemDesc);
		}
		
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
		
		try {
			jedisClient.set(key, JsonUtils.objectToJson(itemDesc));
			jedisClient.expire(key, REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult getItemParam(Long id) {
		String key = REDIS_ITEM_KEY + ":" + id + ":param";

		String jedisResult = jedisClient.get(key);
		if (!StringUtils.isBlank(jedisResult)) {
			TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(jedisResult, TbItemParamItem.class);
			return TaotaoResult.ok(itemParamItem);
		}
		
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		
		try {
			jedisClient.set(key, JsonUtils.objectToJson(list.get(0)));
			jedisClient.expire(key, REDIS_ITEM_EXPIRE);
			return TaotaoResult.ok(list.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, e.getMessage());
		}
	}

}
