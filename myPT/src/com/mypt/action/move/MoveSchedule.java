package com.mypt.action.move;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;

public class MoveSchedule implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id=request.getParameter("id");
		session.setAttribute("uid", id);
		UserDao dao=UserDao.getInstance();
		UserDto dto=dao.getUserById(id);
		int ptCount=dto.getPtcount();
		
		session.setAttribute("ptCount", ptCount);
		request.setAttribute("user", dto);
		return "trainer/schedule";
	}

}
