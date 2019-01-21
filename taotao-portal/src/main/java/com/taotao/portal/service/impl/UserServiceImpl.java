package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.result.TaotaoResult;
import com.taotao.utils.HttpClientUtil;

@Service
public class UserServiceImpl implements UserService {
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;

	@Override
	public TbUser getUserByToken(String token) {
		// TODO Auto-generated method stub
		try {
			String userJson = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(userJson, TbUser.class);
			if (taotaoResult.getStatus() == 200) {
				return (TbUser) taotaoResult.getData();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
