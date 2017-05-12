package com.service.imp;

import java.util.List;
import java.util.Map;

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
	public List<ExamPaper> getAllExamPaperByPage(int startPos, int pageSize,
			ExamPaper examPaper) {
		// TODO Auto-generated method stub
		return examMapper.getAllExamPaperByPage(startPos,pageSize,examPaper.getPaperName(),
				examPaper.getExamType(),examPaper.getQuesIds(),examPaper.getState(),examPaper.getToGroup(),
				examPaper.getCreateUser(),examPaper.getCreateTime(),examPaper.getDescription());
	}

	@Override
	public List<ExamPaper> getType() {
		// TODO Auto-generated method stub
		return examMapper.getType();
	}

	@Override
	public List<ExamPaper> getExamByType(String examType) {
		// TODO Auto-generated method stub
		return examMapper.getExamByType(examType);
	}

	@Override
	public int getExamPaperCount(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		return examMapper.getExamPaperCount(examPaper);
	}

	@Override
	public boolean checkPaperName(int paperID , String paperName) {
		// TODO Auto-generated method stub
		String checkRes = examMapper.checkPaperName(paperID,paperName);
		if(null != checkRes ){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void updatePaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		examMapper.updatePaper(examPaper);
	}

	@Override
	public void insertPaper(ExamPaper examPaper) {
		// TODO Auto-generated method stub
		examMapper.insertPaper(examPaper);
	}

}
