package com.service;

import java.util.List;

import com.model.Question;

public interface QuestionService {

	int getQuesCount();

	List<Question> getAllQuesByPage(int startPos, int pageSize, Question ques);
	
}
