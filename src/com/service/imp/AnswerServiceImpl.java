package com.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AnswerMapper;
import com.model.Answer;
import com.service.AnswerService;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService{
	@Resource
	private AnswerMapper answerMapper;

	@Override
	public void addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		answerMapper.insertAnswer(answer);
	}
}
