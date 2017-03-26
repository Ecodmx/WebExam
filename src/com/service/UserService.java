package com.service;

import java.util.List;

import com.model.Menu;
import com.model.User;

public interface UserService {
	List<User> findAllUser();

	List<User> getUserById(Integer userId);
	
	int getUsernameAndPassword(String username , String password  );
	
	void insertUser(User user);
	
	Menu getMenu();
}
