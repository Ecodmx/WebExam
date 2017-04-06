package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Menu;

public interface MenuMapper {

	List<Menu> getMenu();

	int getMenuCount();

	List<Menu> getRootMenu();

	List<Menu> getAllMenuByPage(@Param(value="startPos")int startPos, @Param(value="pageSize")int pageSize, @Param(value="menuCode")String menuCode,
			@Param(value="menuName")String menuName, @Param(value="parentCode")String parentCode,@Param(value="icon")String icon, @Param(value="uri")String uri, @Param(value="visible")String visible);
	
}
