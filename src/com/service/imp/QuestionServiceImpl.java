package com.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.QuesMapper;
import com.dao.RoleMapper;
import com.model.Question;
import com.service.QuestionService;
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	@Resource
	 public QuesMapper quesMapper;
	@Override
	public int getQuesCount() {
		// TODO Auto-generated method stub
		return quesMapper.getQuesCount();
	}

	@Override
	public List<Question> getAllQuesByPage(int startPos, int pageSize,
			Question ques) {
		// TODO Auto-generated method stub
		return quesMapper.getAllQuesByPage(startPos,pageSize,ques.getContent(),ques.getSelect1(),ques.getSelect2(),
				ques.getSelect3(),ques.getSelect4(),ques.getAnswer(),ques.getQuesType(),ques.getScore(),
				ques.getCreateUserName(),ques.getCreateTime(),ques.getDescription()
			);
	}

	@Override
	public Question getQuesByID(String id) {
		// TODO Auto-generated method stub
		return quesMapper.getQuesByID(id);
	}

}
