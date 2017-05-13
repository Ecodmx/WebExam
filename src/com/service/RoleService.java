package com.service;

import java.util.List;

import com.model.Role;

public interface RoleService {

	int getRoleCount();

	List<Role> getAllRoleByPage(int startPos, int pageSize, Role role);

	Role getRole(int user_id);

}
