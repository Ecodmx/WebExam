package com.service;

import java.util.List;

import com.model.Menu;
import com.model.User;

public interface UserService {
	List<User> findAllUser();

	List<User> getUserById(Integer userId);
	
	User getUser(String username , String password);
	
	void insertUser(User user);
	
	int getMenuCount(int roleID);
	
	List<Menu> getMenu(int roleID);

	List<Menu> getRootMenu(int roleID);

	List<User> getAllUserByPage(int startPos, int pageSize, User user);

	int getUserCount(User user);

	void delUser(int userId);

	void updateUser(User user);

	boolean checkUserName(int userID ,String user_name);
}
