package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.result.TaotaoResult;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	
	@Override
	public TaotaoResult checkData(String content, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();

		switch (type) {
		case 1:
			criteria.andUsernameEqualTo(content);
			break;
		case 2:
			criteria.andPhoneEqualTo(content);
			break;
		case 3:
			criteria.andEmailEqualTo(content);
			break;
		default:
			break;
		}

		List<TbUser> userResult = userMapper.selectByExample(example);

		if (userResult != null && userResult.size() > 0) {
			return TaotaoResult.ok(false);
		}
		return TaotaoResult.ok(true);
	}

	@Override
	public TaotaoResult addUser(TbUser user) {
		// TODO Auto-generated method stub
		Date date = new Date();
		user.setCreated(date);
		user.setUpdated(date);
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult loginUser(TbUser user, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andUsernameEqualTo(user.getUsername());
		
		List<TbUser> userResult = userMapper.selectByExample(example);
		
		if (userResult != null && userResult.size() > 0) {
			String md5Passwrod = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
			String password = userResult.get(0).getPassword();
			if (password.equals(md5Passwrod)) {
				
				
				TbUser tbUser = userResult.get(0);
				tbUser.setPassword(null);
				String token = UUID.randomUUID().toString();
				
				try {
					jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(tbUser));
					jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//把token添加到cookie中
				CookieUtils.setCookie(request, response, "TT_TOKEN", token);
				
				return TaotaoResult.ok(token);
			}
		}
		
		return TaotaoResult.build(400, "用户名或密码错误");
	}

	@Override
	public TaotaoResult getUserByToken(String token) {
		// TODO Auto-generated method stub
		String jedisResult = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		if (StringUtils.isBlank(jedisResult)) {
			return TaotaoResult.build(400, "token过期");
		}
		TaotaoResult taotaoResult = TaotaoResult.ok(JsonUtils.jsonToPojo(jedisResult, TbUser.class));
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		return taotaoResult;
	}
}
