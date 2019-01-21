package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.utils.CookieUtils;

public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_PAGE_LOGIN}")
	private String SSO_PAGE_LOGIN;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		TbUser user = userService.getUserByToken(token);
		if (user == null) {
			response.sendRedirect(SSO_BASE_URL + SSO_PAGE_LOGIN 
					+ "?redirect=" + request.getRequestURL());
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
