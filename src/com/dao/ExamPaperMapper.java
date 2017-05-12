package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.model.ExamPaper;

public interface ExamPaperMapper {

	int getExamPaperCount(ExamPaper examPaper);

	List<ExamPaper> getAllExamPaperByPage(@Param(value="startPos")int startPos, @Param(value="pageSize")int pageSize,
			@Param(value="paperName")String paperName, @Param(value="examType")String examType, @Param(value="quesIds")String quesIds, @Param(value="state")String state,
			@Param(value="toGroup")String toGroup, @Param(value="createUser")String createUser, @Param(value="createTime")String createTime,@Param(value="description")String description);

	List<ExamPaper> getType();

	List<ExamPaper> getExamByType(@Param(value="examType")String examType);

	String checkPaperName(@Param(value="paperID")int paperID , @Param(value="paperName")String paperName);

	void updatePaper(ExamPaper examPaper);

	void insertPaper(ExamPaper examPaper);

}
