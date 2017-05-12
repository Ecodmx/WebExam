package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.User;

public interface UserMapper {
	List<User>	findAllUser();

	List<User> selectByPrimaryKey(Integer userId);

	int getUsernameAndPassword(@Param("username")String username,@Param("password") String password);

	void insertUser(User user);

	List<User> getAllUserByPage(@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize, @Param(value="username")String username,@Param(value="email") String email);

	int getUserCount(User user);

	void delUser(@Param(value="userId")int userId);

	void updateUser(User user);

	String checkUserName(@Param(value="user_name")String user_name);
}
