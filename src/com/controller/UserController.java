package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Page;
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

	//获取所有用户信息 （分页）
	@RequestMapping("/getAllUser")
	@ResponseBody
	public String getAllUser(HttpServletRequest request,User user){
		String pageNow =request.getParameter("start");
		String pageSize = request.getParameter("length");
		int draw = Integer.valueOf(request.getParameter("draw")) ;//datatables参数
		Page page = null;  
		List<User> allUser;
		
		int totalCount = userService.getUserCount(); //获取分页总数
	    if (pageNow != null) {  
	        page = new Page(totalCount, Integer.parseInt(pageNow) , Integer.parseInt(pageSize));  
	        allUser = userService.getAllUserByPage(page.getStartPos(), page.getPageSize(),user);  
	    } else {  
	        page = new Page(totalCount, 1 , 10);  
	        allUser = userService.getAllUserByPage(page.getStartPos(), page.getPageSize(),user);  
	    }  
		
		JSONArray ja = JSONArray.fromObject(allUser);
		StringBuilder sb = new StringBuilder("{\"draw\":"+draw+",\"recordsTotal\":"+totalCount+",\"recordsFiltered\":"+totalCount+",\"data\":");
		sb.append(ja.toString());
		sb.append("}");
		return sb.toString();
	}
}
