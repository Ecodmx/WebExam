package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		String pageNow =request.getParameter("start");//分页参数
		String pageSize = request.getParameter("length");//分页参数
		int draw = Integer.valueOf(request.getParameter("draw")) ;//datatables参数
		Page page = null;  
		List<User> allUser;
		
		int totalCount = userService.getUserCount(user); //获取分页总数
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
		sb.append("}");//拼接dataTables的参数 （感觉不太规范）
		return sb.toString();
	}
	
	@RequestMapping(value = "/saveUser" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String saveUser(HttpServletRequest request,User user){
		JSONObject jo =new JSONObject();
		int userid = user.getUser_id();
		boolean checkRes = userService.checkUserName(userid,user.getUser_name());
		if(checkRes){
			jo.put("flag", "true");
			if(userid>0){
				userService.updateUser(user);
				jo.put("msg", "用户修改成功");
			}else{
				userService.insertUser(user);
				jo.put("msg", "用户新增成功");
			}
		}else{
			jo.put("flag", "false");
			jo.put("msg", "用户名重复,请检查");
		}
		
		
		return jo.toString();
	}
	@RequestMapping(value = "/delUser" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String deleteUser(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("userID"));
		//userService.insertUser(user);
		userService.delUser(id);
		JSONObject jo =new JSONObject();
		jo.put("msg", "true");
		return jo.toString();
	}
}
