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
	public User getUser(String username, String password) {
		// TODO Auto-generated method stub
		return userMapper.getUser(username , password);
		
	}
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insertUser(user);
	}
	@Override
	public List<Menu> getMenu(int roleID) {
		// TODO Auto-generated method stub
		return menuMapper.getMenu(roleID);
	}
	@Override
	public int getMenuCount(int roleID) {
		// TODO Auto-generated method stub
		return menuMapper.getMenuCount(roleID);
	}
	@Override
	public List<Menu> getRootMenu(int roleID) {
		// TODO Auto-generated method stub
		return menuMapper.getRootMenu(roleID);
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
	public boolean checkUserName(int userID , String user_name) {
		// TODO Auto-generated method stub
		String checkRes = userMapper.checkUserName(userID , user_name);
		if(null != checkRes ){
			return false;
		}else{
			return true;
		}
		
	}

}
