package com.mypt.action.check;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;

public class NickCheckAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		UserDao dao = UserDao.getInstance();
		
		int result = dao.checkNick(request.getParameter("nickname"));
		request.setAttribute("result", result);
		
		return "callback";
	}

}
