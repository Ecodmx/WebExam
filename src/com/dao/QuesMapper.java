package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Question;

public interface QuesMapper {

	int getQuesCount();

	List<Question> getAllQuesByPage(@Param(value="startPos")int startPos,@Param(value="pageSize") int pageSize,@Param(value="content") String content,
			@Param(value="select1")String select1, @Param(value="select2")String select2, @Param(value="select3")String select3,@Param(value="select4") String select4,
			@Param(value="answer")String answer, @Param(value="quesType")String quesType,@Param(value="score") String score,
			@Param(value="createUserName")String createUserName,@Param(value="createTime") String createTime, @Param(value="description")String description);

}
