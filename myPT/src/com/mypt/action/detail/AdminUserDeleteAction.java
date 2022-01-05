package com.mypt.action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;

public class AdminUserDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDao dao=UserDao.getInstance();
		String id=request.getParameter("id");
		dao.deleteUser(id);
		
		return "redirect:userList.do";
	}

}
