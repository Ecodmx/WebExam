package com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Answer;
import com.model.Page;
import com.model.User;
import com.service.AnswerService;

@Controller
@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private AnswerService answerService;
	
	@RequestMapping(value = "/addAnswer" , produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String addAnswer (HttpServletRequest request ){
		String answers = request.getParameter("answer");
		String score = request.getParameter("score");
		Answer answer = new Answer();
		JSONObject jo = new JSONObject();
		answer.setAnswers(answers);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String answerTime=format.format(date);
		answer.setAnswerTime(answerTime);
		answer.setUserID(1);
		answer.setPaperID(1);
		answer.setScore(score);
		answerService.addAnswer(answer);
		jo.put("msg", "添加成功");
		return jo.toString();
		
	}
	
	@RequestMapping("/getAnswer")
	@ResponseBody
	public String getAllAnswer (HttpSession session,HttpServletRequest request,Answer answer ){
		String pageNow =request.getParameter("start");//分页参数
		String pageSize = request.getParameter("length");//分页参数
		int draw = Integer.valueOf(request.getParameter("draw")) ;//datatables参数
		User user = (User) session.getAttribute("User");
		int userID = user.getUser_id();
		Page page = null;  
		List<Answer> allAnswer;
		
		int totalCount = answerService.getAnswerCount(answer,userID); //获取分页总数
	    if (pageNow != null) {  
	        page = new Page(totalCount, Integer.parseInt(pageNow) , Integer.parseInt(pageSize));  
	        allAnswer = answerService.getAllAnswerByPage(page.getStartPos(), page.getPageSize(),answer,userID);  
	    } else {  
	        page = new Page(totalCount, 1 , 10);
	        allAnswer = answerService.getAllAnswerByPage(page.getStartPos(), page.getPageSize(),answer,userID);  
	    }  
		
		JSONArray ja = JSONArray.fromObject(allAnswer);
		StringBuilder sb = new StringBuilder("{\"draw\":"+draw+",\"recordsTotal\":"+totalCount+",\"recordsFiltered\":"+totalCount+",\"data\":");
		sb.append(ja.toString());
		sb.append("}");//拼接dataTables的参数 （感觉不太规范）
		return sb.toString();
		
	}

}
