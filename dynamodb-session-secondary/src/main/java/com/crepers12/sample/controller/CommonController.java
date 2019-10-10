package com.crepers12.sample.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crepers12.sample.model.SharedSession;
import com.crepers12.sample.model.User;

@Controller
public class CommonController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("sessionId", request.getSession().getId());

		return "login";
	}

	@RequestMapping(value = "/login")
	public String main(@Validated SharedSession userSession, Model model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("login Page Requested");
		if(userSession == null || userSession.getUserSessionId() == null || userSession.getUserSessionId().isEmpty()) {
			System.out.println("user session information is null");
			return "login";
		}
				
		Cookie uiColorCookie = new Cookie("SESSION", userSession.getUserSessionId());
		response.addCookie(uiColorCookie);
		
		model.addAttribute("sessionId", userSession.getUserSessionId());
		
		return "main";
	}
	
	@RequestMapping(value = "/user")
	public String user(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		if(session.getAttribute("member") != null) {
			User user = (User)session.getAttribute("member");
			model.addAttribute("userName", user.getUserName());
			model.addAttribute("sessionId", request.getSession().getId());
			return "main";
		}
		
		return "main";
	}
	
	
}
