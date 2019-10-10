package com.crepers12.sample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.crepers12.sample.model.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static final String LOGIN = "login";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("LoginInsteceptor..." + request.getContextPath());
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		User member = (User)session.getAttribute("member");
		
		System.out.println(request.getRequestURI());
		if(member == null && !request.getRequestURI().equals("user")) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		return true;
	}
}
