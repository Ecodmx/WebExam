package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Menu;
import com.model.Page;
import com.model.Role;
import com.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	

	@RequestMapping(value = "/getAllRole" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getAllMenu(HttpServletRequest request,Role role){
		String pageNow =request.getParameter("start");
		String pageSize = request.getParameter("length");
		int draw = Integer.valueOf(request.getParameter("draw")) ;//datatables参数
		Page page = null;  
		List<Role> allMenu;
		
		int totalCount = roleService.getRoleCount(); //获取分页总数
	    if (pageNow != null) {
	        page = new Page(totalCount, Integer.parseInt(pageNow) , Integer.parseInt(pageSize));  
	        allMenu = roleService.getAllRoleByPage(page.getStartPos(), page.getPageSize(),role);  
	    } else {  
	        page = new Page(totalCount, 1 , 10);  
	        allMenu = roleService.getAllRoleByPage(page.getStartPos(), page.getPageSize(),role);  
	    }  
		
		JSONArray ja = JSONArray.fromObject(allMenu);
		StringBuilder sb = new StringBuilder("{\"draw\":"+draw+",\"recordsTotal\":"+totalCount+",\"recordsFiltered\":"+totalCount+",\"data\":");
		sb.append(ja.toString());
		sb.append("}");
		return sb.toString();
	}
}
