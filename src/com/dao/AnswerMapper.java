package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Answer;

public interface AnswerMapper {

	void insertAnswer(Answer answer);

	int getAnswerCount(@Param(value="answerTime")String answerTime, @Param(value="userID")int userID);

	List<Answer> getAllAnswerByPage(@Param(value="startPos")int startPos, @Param(value="pageSize")int pageSize,@Param(value="answerTime")String answerTime ,@Param(value="userID")int userID);

}
