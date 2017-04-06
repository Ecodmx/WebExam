package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Menu;
import com.model.Page;
import com.model.User;

import com.service.MenuService;
import com.service.UserService;
import com.util.ActionHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller

public class MenuController {
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@RequestMapping(value = "/getMenu" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String  getMenu()  throws Exception{
		int menuCount = userService.getMenuCount();
		List<Menu> menu = userService.getRootMenu();
		StringBuilder sb = new StringBuilder("");
		
		List<Menu> childMenu =userService.getMenu()  ;
		int count = 0;//menu计数
//		for(int j = 0;j<menu.size();j++){
//			if(!(menu.get(j).getParentCode().equals("ROOT"))){
//				childMenu.add(menu.get(j));
//			}
//			
//		}
		if(menuCount > 0){
			sb.append("[");
			for(int i = 0;i<menu.size();i++){
				sb.append("{\n");
				sb.append("\"id\":\""+menu.get(i).getMenuCode()+"\",\n");
				sb.append("\"text\":\""+menu.get(i).getMenuName()+"\",\n");
				sb.append("\"icon\":\""+menu.get(i).getIcon()+"\",\n");
				sb.append("\"url\":\""+menu.get(i).getUri()+"\"");
				if(menu.get(i).getParentCode().equals("ROOT")){
					count = 0;
					for(int ii =0; ii<childMenu.size();ii++){
						if(menu.get(i).getMenuCode().equals(childMenu.get(ii).getParentCode())){
							count++;	
							if(count == 1){
								sb.append(",\n\"menus\": [{");
							}else{
								sb.append(",{");
							}
							sb.append("\"id\":\""+childMenu.get(ii).getMenuCode()+"\",");
							sb.append("\"text\":\""+childMenu.get(ii).getMenuName()+"\",");
							sb.append("\"icon\":\""+childMenu.get(ii).getIcon()+"\",");
							sb.append("\"url\":\""+childMenu.get(ii).getUri()+"\"");
							sb.append("}");
						}
					}
					sb.append("]");
					
				}
				if(i<menu.size()-1){
					sb.append("},");
				}else{
					sb.append("}");
				}
				
			}
			sb.append("]");
			
			
		}
		return sb.toString();
		
	}
	
	//获取所有用户信息 （分页）

	@RequestMapping(value = "/getAllMenu" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getAllMenu(HttpServletRequest request,Menu menu){
		String pageNow =request.getParameter("start");
		String pageSize = request.getParameter("length");
		int draw = Integer.valueOf(request.getParameter("draw")) ;//datatables参数
		Page page = null;  
		List<Menu> allMenu;
		
		int totalCount = menuService.getMenuCount(); //获取分页总数
	    if (pageNow != null) {
	        page = new Page(totalCount, Integer.parseInt(pageNow) , Integer.parseInt(pageSize));  
	        allMenu = menuService.getAllMenuByPage(page.getStartPos(), page.getPageSize(),menu);  
	    } else {  
	        page = new Page(totalCount, 1 , 10);  
	        allMenu = menuService.getAllMenuByPage(page.getStartPos(), page.getPageSize(),menu);  
	    }  
		
		JSONArray ja = JSONArray.fromObject(allMenu);
		StringBuilder sb = new StringBuilder("{\"draw\":"+draw+",\"recordsTotal\":"+totalCount+",\"recordsFiltered\":"+totalCount+",\"data\":");
		sb.append(ja.toString());
		sb.append("}");
		return sb.toString();
	}
}
