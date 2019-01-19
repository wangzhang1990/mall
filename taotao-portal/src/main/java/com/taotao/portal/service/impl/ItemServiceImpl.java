package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.TbItem;
import com.taotao.portal.service.ItemService;
import com.taotao.result.TaotaoResult;
import com.taotao.utils.HttpClientUtil;
import com.taotao.utils.JsonUtils;

@Service
public class ItemServiceImpl implements ItemService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_URL}")
	private String REST_ITEM_URL;

	@Override
	public TaotaoResult getItemBaseInfo(Long itemId) {
		String restResult = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_URL + "/" + itemId);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(restResult, TbItem.class);
		return taotaoResult;
	}

	@Override
	public TaotaoResult getItemDesc(Long itemId) {
		String restResult = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_URL + "/desc/" + itemId);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(restResult, TbItemDesc.class);
		return taotaoResult;
	}

	@Override
	public String getItemParam(Long itemId) {
		// TODO Auto-generated method stub
		String restResult = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_URL + "/param/" + itemId);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(restResult, TbItemParamItem.class);
		if (taotaoResult.getStatus() == 200) {
			TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
			String paramData = itemParamItem.getParamData();
			// 生成html
			// 把规格参数json数据转换成java对象
			List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
			StringBuffer sb = new StringBuffer();
			sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
			sb.append("    <tbody>\n");
			for (Map m1 : jsonList) {
				sb.append("        <tr>\n");
				sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
				sb.append("        </tr>\n");
				List<Map> list2 = (List<Map>) m1.get("params");
				for (Map m2 : list2) {
					sb.append("        <tr>\n");
					sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
					sb.append("            <td>" + m2.get("v") + "</td>\n");
					sb.append("        </tr>\n");
				}
			}
			sb.append("    </tbody>\n");
			sb.append("</table>");
			// 返回html片段
			return sb.toString();
		}
		return "";

	}
}
