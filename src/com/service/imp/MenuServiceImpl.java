package com.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.MenuMapper;
import com.model.Menu;
import com.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	@Resource
	public MenuMapper menuMapper;
	@Override
	public List<Menu> getAllMenuByPage(int startPos, int pageSize, Menu menu) {
		// TODO Auto-generated method stub
		return menuMapper.getAllMenuByPage(startPos,pageSize,menu.getMenuCode(),menu.getMenuName(),menu.getParentCode(),menu.getIcon(),menu.getUri(),menu.getVisible());
	}
	@Override
	public int getMenuCount() {
		// TODO Auto-generated method stub
		return menuMapper.getMenuCount();
	}

}
