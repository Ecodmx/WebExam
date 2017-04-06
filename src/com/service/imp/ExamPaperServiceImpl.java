package com.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ExamPaperMapper;
import com.model.ExamPaper;
import com.service.ExamPaperService;
@Service
@Transactional
public class ExamPaperServiceImpl implements ExamPaperService {
	@Resource
	private ExamPaperMapper examMapper;
	@Override
	public int getExamPaperCount() {
		// TODO Auto-generated method stub
		return examMapper.getExamPaperCount();
	}

	@Override
	public List<ExamPaper> getAllExamPaperByPage(int startPos, int pageSize,
			ExamPaper examPaper) {
		// TODO Auto-generated method stub
		return examMapper.getAllExamPaperByPage(startPos,pageSize,examPaper.getPaperName(),
				examPaper.getExamType(),examPaper.getQuesIds(),examPaper.getState(),examPaper.getToGroup(),
				examPaper.getCreateUser(),examPaper.getCreateTime(),examPaper.getDescription());
	}

}
