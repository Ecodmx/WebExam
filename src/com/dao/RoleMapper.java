package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Role;

public interface RoleMapper {

	int getRoleCount();

	List<Role> getAllRoleByPage(@Param(value="startPos")int startPos,@Param(value="pageSize") int pageSize, @Param(value="roleName")String roleName,
			@Param(value="group")String group);

	Role getRoleByID(@Param(value="user_id")int user_id);


}
