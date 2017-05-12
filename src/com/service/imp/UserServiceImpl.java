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
	public List<Menu> getMenu() {
		// TODO Auto-generated method stub
		return menuMapper.getMenu();
	}
	@Override
	public int getMenuCount() {
		// TODO Auto-generated method stub
		return menuMapper.getMenuCount();
	}
	@Override
	public List<Menu> getRootMenu() {
		// TODO Auto-generated method stub
		return menuMapper.getRootMenu();
	}
	@Override
	public List<User> getAllUserByPage(int startPro , int pageSize , User user) {
		// TODO Auto-generated method stub
		return userMapper.getAllUserByPage(startPro,pageSize,user.getUser_name() , user.getE_mail());
	}
	@Override
	public int getUserCount(User user) {
		// TODO Auto-generated method stub
		return userMapper.getUserCount(user);
	}
	@Override
	public void delUser(int userId) {
		// TODO Auto-generated method stub
		userMapper.delUser(userId);
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}
	@Override
	public boolean checkUserName(String user_name) {
		// TODO Auto-generated method stub
		String checkRes = userMapper.checkUserName(user_name);
		if(null != checkRes ){
			return false;
		}else{
			return true;
		}
		
	}

}
