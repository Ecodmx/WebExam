package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Menu;

public interface MenuMapper {

	List<Menu> getMenu(@Param(value="roleID")int roleID);

	int getMenuCount(@Param(value="roleID")int roleID);

	List<Menu> getRootMenu(@Param(value="roleID")int roleID);

	List<Menu> getAllMenuByPage(@Param(value="startPos")int startPos, @Param(value="pageSize")int pageSize, @Param(value="menuCode")String menuCode,
			@Param(value="menuName")String menuName, @Param(value="parentCode")String parentCode,@Param(value="icon")String icon, @Param(value="uri")String uri, @Param(value="visible")String visible);

	int getMenuCountByConditions();
	
}
