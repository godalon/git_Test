package com.mypt.action.history;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.HistoryDao;

public class AdminHistoryDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String hid=request.getParameter("hid");
		HistoryDao hdao=HistoryDao.getInstance();
		hdao.deleteHistory(hid);
		return "redirect:adminHistory.do";
	}

}
