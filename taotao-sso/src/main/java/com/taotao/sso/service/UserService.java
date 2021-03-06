package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.pojo.TbUser;
import com.taotao.result.TaotaoResult;

public interface UserService {

	TaotaoResult checkData(String content, Integer type);

	TaotaoResult addUser(TbUser user);

	TaotaoResult loginUser(TbUser user, HttpServletRequest request, HttpServletResponse response);

	TaotaoResult getUserByToken(String token);

}
