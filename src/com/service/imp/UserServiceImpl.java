package com.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MenuMapper;
import com.dao.UserMapper;
import com.model.Menu;
import com.model.User;
import com.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;
    @Resource
    public MenuMapper menuMapper;
    @Override
    public List<User> findAllUser() {
        // TODO Auto-generated method stub
        List<User> findAllUser = userMapper.findAllUser();
        return findAllUser;
    }
	@Override
	public List<User> getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return  userMapper.selectByPrimaryKey(userId);
	}
	@Override
	public int getUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return userMapper.getUsernameAndPassword(username , password);
		
	}
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insertUser(user);
	}
	@Override
	public Menu getMenu() {
		// TODO Auto-generated method stub
		return menuMapper.getMenu();
	}

}
