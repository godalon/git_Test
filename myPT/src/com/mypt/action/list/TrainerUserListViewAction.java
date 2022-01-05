package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;

public class TrainerUserListViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDao dao=UserDao.getInstance();
		HttpSession session = request.getSession();
		String tid=(String) session.getAttribute("id");
		ArrayList<UserDto> arr=dao.ptUserList(tid);
		
		request.setAttribute("ptUserList", arr);
		return "trainer/ptUserList";
	}

}
