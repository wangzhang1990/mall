package com.taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;
import com.taotao.utils.JsonUtils;

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	//@SuppressWarnings(value = { "all" })
	public String getItemParamByItemId(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.isEmpty()) {
			return "";
		}
		
		String paramData = list.get(0).getParamData();
		List<Map> jsonToList = JsonUtils.jsonToList(paramData, Map.class);
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"ptable\">\r\n" ); 
		sb.append("			<tbody>\r\n" );  
		for (Map map1 : jsonToList) {
			sb.append("				<tr>\r\n" );  
			sb.append("					<th class=\"tdTitle\" colspan=\"2\">" + map1.get("group") + "</th>\r\n" );  
			sb.append("				</tr>\r\n" ); 
			
			for (Map map2 : (List<Map>) map1.get("params")) {
				sb.append("				<tr>\r\n" );  
				sb.append("					<td class=\"tdTitle\">" + map2.get("k") + "</td>\r\n" );  
				sb.append("					<td>" + map2.get("v") +  "</td>\r\n" );  
				sb.append("				</tr>\r\n" );  
			}
		}
		sb.append("			</tbody>\r\n" );  
		sb.append("		</table>");
		
		
		return sb.toString();
	}

}
