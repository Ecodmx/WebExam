package com.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.User;
import com.service.UserService;
@Controller

public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping("/login") 
	public String executeLogin(HttpSession session,HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User currentUser = userService.getUser(username, password);
		if(null != currentUser ){
			session.setAttribute("User", currentUser);
			return "/index.jsp";
		}
		return "/login.jsp" ;
		
	}
	@RequestMapping("/loginOut")  
    public String loginOut(HttpSession httpSession){  
        httpSession.invalidate();  
        return "/login.jsp";  
    }  

}
