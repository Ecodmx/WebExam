package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Page;
import com.model.Question;
import com.model.User;
import com.service.QuestionService;
import com.service.UserService;

@Controller
@RequestMapping("/ques")
public class QuesController {
	@Autowired
	private QuestionService quesService;
	
	//获取所有用户信息 （分页）
		
		@RequestMapping(value = "/getAllQuestion" , produces = {"application/json;charset=UTF-8"})
		@ResponseBody
		public String getAllQuestion(HttpServletRequest request,Question ques){
			String pageNow =request.getParameter("start");
			String pageSize = request.getParameter("length");
			int draw = Integer.valueOf(request.getParameter("draw")) ;//datatables参数
			Page page = null;  
			List<Question> allQues;
			int totalCount = quesService.getQuesCount(); //获取分页总数
		    if (pageNow != null) {  
		        page = new Page(totalCount, Integer.parseInt(pageNow) , Integer.parseInt(pageSize));  
		        allQues = quesService.getAllQuesByPage(page.getStartPos(), page.getPageSize(),ques);  
		    } else {  
		        page = new Page(totalCount, 1 , 10);  
		        allQues = quesService.getAllQuesByPage(page.getStartPos(), page.getPageSize(),ques);  
		    }  
			
			JSONArray ja = JSONArray.fromObject(allQues);
			StringBuilder sb = new StringBuilder("{\"draw\":"+draw+",\"recordsTotal\":"+totalCount+",\"recordsFiltered\":"+totalCount+",\"data\":");
			sb.append(ja.toString());
			sb.append("}");
			return sb.toString();
		}
	
}
