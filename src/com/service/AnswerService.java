package com.service;

import java.util.List;

import com.model.Answer;


public interface AnswerService {

	void addAnswer(Answer answer);

	int getAnswerCount(Answer answer, int userID);

	List<Answer> getAllAnswerByPage(int startPos, int pageSize,Answer answer,int userID);


}
