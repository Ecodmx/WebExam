package com.service;

import java.util.List;
import java.util.Map;

import com.model.ExamPaper;

public interface ExamPaperService {

	int getExamPaperCount(ExamPaper examPaper);

	List<ExamPaper> getAllExamPaperByPage(int startPos, int pageSize,
			ExamPaper examPaper);

	List<ExamPaper> getType();



	List<ExamPaper> getExamByType(String examType);

	boolean checkPaperName(int paperID , String paperName);

	void updatePaper(ExamPaper examPaper);

	void insertPaper(ExamPaper examPaper);
	
}
