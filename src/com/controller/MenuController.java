package com.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Menu;
import com.service.UserService;
import com.util.ActionHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller

public class MenuController {
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/getMenu" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String  getMenu()  throws Exception{
		Menu menu = userService.getMenu();
		JSONObject json =JSONObject.fromObject(menu);
		
		return json.toString();
		
	}
}
