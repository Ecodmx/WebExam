package com.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AnswerMapper;
import com.model.Answer;
import com.model.User;
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

	@Override
	public int getAnswerCount(Answer answer, int userID) {
		// TODO Auto-generated method stub
		return answerMapper.getAnswerCount(answer.getAnswerTime(), userID);
	}

	@Override
	public List<Answer> getAllAnswerByPage(int startPos, int pageSize, Answer answer ,int userID) {
		// TODO Auto-generated method stub
		return answerMapper.getAllAnswerByPage(startPos,pageSize,answer.getAnswerTime(),userID);
	}
}
