package com.service;

import java.util.List;

import com.model.Menu;
import com.model.User;

public interface UserService {
	List<User> findAllUser();

	List<User> getUserById(Integer userId);
	
	int getUsernameAndPassword(String username , String password  );
	
	void insertUser(User user);
	
	int getMenuCount();
	
	List<Menu> getMenu();

	List<Menu> getRootMenu();

	List<User> getAllUserByPage(int startPos, int pageSize, User user);

	int getUserCount(User user);

	void delUser(int userId);

	void updateUser(User user);

	boolean checkUserName(String user_name);
}
