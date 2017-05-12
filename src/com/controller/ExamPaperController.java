package com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.ExamPaper;
import com.model.Page;
import com.model.Question;
import com.model.User;
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
		int totalCount = examService.getExamPaperCount(examPaper); //获取分页总数
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
	@RequestMapping(value = "/getType" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getType(HttpServletRequest request){
		List<ExamPaper> tppeList = examService.getType();
		JSONArray ja = JSONArray.fromObject(tppeList);
		return ja.toString();
	}
	@RequestMapping(value = "/getExam" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getExam(HttpServletRequest request){
		String examType = request.getParameter("examtype");
		List<ExamPaper> examList = examService.getExamByType(examType);
		JSONArray ja = JSONArray.fromObject(examList);
		return ja.toString();
	}
	@RequestMapping(value = "/savePaper" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String savePaper(HttpServletRequest request,ExamPaper examPaper){
		JSONObject jo =new JSONObject();
		int paperid = examPaper.getPaperID();
		boolean checkRes = examService.checkPaperName(paperid , examPaper.getPaperName());
		examPaper.setCreateUser("zhangsan");
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		examPaper.setCreateTime(time);
		
		
		if(checkRes){
			jo.put("flag", "true");
			if(paperid>0){
				examService.updatePaper(examPaper);
				jo.put("msg", "用户修改成功");
			}else{
				examService.insertPaper(examPaper);
				jo.put("msg", "用户新增成功");
			}
		}else{
			jo.put("flag", "false");
			jo.put("msg", "试题名称重复,请检查");
		}
		
		
		return jo.toString();
	}
}
