package com.crepers12.sample.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String user(@Validated User user, Model model, HttpServletRequest request) {
		System.out.println("login Page Requested");
		
		HttpSession session = request.getSession();

		if(session.getAttribute("member") != null) {
			user = (User)session.getAttribute("member");
			model.addAttribute("userName", user.getUserName());
			model.addAttribute("sessionId", request.getSession().getId());
			return "main";
		}
		
		if(user == null || user.getUserName() == null || user.getUserName().isEmpty()) {
			System.out.println("user info is null");
			return "login";
		}
		
		session = request.getSession(true);

		session.setAttribute("member", user);
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("sessionId", request.getSession().getId());
		
		return "main";
	}
}
