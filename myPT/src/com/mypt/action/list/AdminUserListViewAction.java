package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;

public class AdminUserListViewAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDao dao=UserDao.getInstance();
		
		ArrayList<UserDto> arr=dao.userList();
		
		request.setAttribute("userList", arr);
		return "admin/userList";
	}
	
}
