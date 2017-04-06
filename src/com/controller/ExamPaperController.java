package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.ExamPaper;
import com.model.Page;
import com.model.Question;
import com.service.ExamPaperService;

@Controller
@RequestMapping("/exam")
public class ExamPaperController {
	@Autowired
	private ExamPaperService examService;
	
	//获取所有用户信息 （分页）
	
	@RequestMapping(value = "/getAllExamPaper" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getAllQuestion(HttpServletRequest request,ExamPaper examPaper){
		String pageNow =request.getParameter("start");
		String pageSize = request.getParameter("length");
		int draw = Integer.valueOf(request.getParameter("draw")) ;//datatables参数
		Page page = null;  
		List<ExamPaper> allPaper;
		int totalCount = examService.getExamPaperCount(); //获取分页总数
	    if (pageNow != null) {  
	        page = new Page(totalCount, Integer.parseInt(pageNow) , Integer.parseInt(pageSize));  
	        allPaper = examService.getAllExamPaperByPage(page.getStartPos(), page.getPageSize(),examPaper);  
	    } else {  
	        page = new Page(totalCount, 1 , 10);  
	        allPaper = examService.getAllExamPaperByPage(page.getStartPos(), page.getPageSize(),examPaper);  
	    }  
		
		JSONArray ja = JSONArray.fromObject(allPaper);
		StringBuilder sb = new StringBuilder("{\"draw\":"+draw+",\"recordsTotal\":"+totalCount+",\"recordsFiltered\":"+totalCount+",\"data\":");
		sb.append(ja.toString());
		sb.append("}");
		return sb.toString();
	}
	
}
