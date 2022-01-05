package com.mypt.action.qr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;

public class QrViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDao dao=UserDao.getInstance();
		HttpSession session= request.getSession();
		String id=(String) session.getAttribute("id");
		String qr=dao.getQR(id);
		
		request.setAttribute("result", qr);
		
		return "callback";
	}

}
