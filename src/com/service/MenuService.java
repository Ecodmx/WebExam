package com.service;

import java.util.List;

import com.model.Menu;


public interface MenuService {
	List<Menu> getAllMenuByPage(int startPos, int pageSize, Menu menu);

	int getMenuCount();


}
