package com.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RoleMapper;

import com.model.Role;
import com.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	 @Resource
	 public RoleMapper roleMapper;

	@Override
	public int getRoleCount() {
		// TODO Auto-generated method stub
		return roleMapper.getRoleCount();
	}

	@Override
	public List<Role> getAllRoleByPage(int startPos, int pageSize, Role role) {
		// TODO Auto-generated method stub
		return roleMapper.getAllRoleByPage(startPos,pageSize,role.getRoleName(),role.getGroup());
	}

	@Override
	public Role getRole(int user_id) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleByID(user_id);
	}
}
