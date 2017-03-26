package com.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.service.UserService;
@Controller

public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login") 
	public String executeLogin(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int loginSuccess = userService.getUsernameAndPassword(username, password);
		if(loginSuccess > 0 ){
			return "/index.jsp";
		}
		return "/login.jsp" ;
		
	}

}
