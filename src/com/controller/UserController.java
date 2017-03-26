package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.User;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/findAllUser") 
	public String findAllUser(HttpServletRequest request){
		   List<User> listUser =  userService.findAllUser();
		   request.setAttribute("listUser", listUser);
		   return "/allUser.jsp";
	}
	
	public List<User> getUserById(HttpServletRequest request){
		Integer userId =Integer.parseInt( request.getParameter("id"));
		return userService.getUserById(userId);
		
	}
	@RequestMapping("/register") 
	public String registerUser(HttpServletRequest request,User user){
		userService.insertUser(user);
		System.out.println(user.getE_mail()+" " + user.getUser_name());
		if(null == user.getDescription()){
			user.setDescription("");
		}
		request.setAttribute("msg", "success");
		return "/index.jsp";
	}
}
