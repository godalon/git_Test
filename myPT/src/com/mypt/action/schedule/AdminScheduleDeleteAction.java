package com.mypt.action.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.ScheduleDao;

public class AdminScheduleDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String s_id=request.getParameter("s_id");
		String s_date=request.getParameter("s_date");
		ScheduleDao sdao=ScheduleDao.getInstance();
		sdao.ScheduleDelete(s_id, s_date);
		
		return "redirect:adminSchedule.do";
	}

}
