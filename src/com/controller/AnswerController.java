package com.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Answer;
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
}
