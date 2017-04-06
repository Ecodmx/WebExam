package com.service;

import java.util.List;

import com.model.ExamPaper;

public interface ExamPaperService {

	int getExamPaperCount();

	List<ExamPaper> getAllExamPaperByPage(int startPos, int pageSize,
			ExamPaper examPaper);
	
}
