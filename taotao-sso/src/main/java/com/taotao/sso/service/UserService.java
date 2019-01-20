package com.taotao.sso.service;

import com.taotao.pojo.TbUser;
import com.taotao.result.TaotaoResult;

public interface UserService {

	TaotaoResult checkData(String content, Integer type);

	TaotaoResult addUser(TbUser user);

	TaotaoResult loginUser(TbUser user);

	TaotaoResult getUserByToken(String token);

}
