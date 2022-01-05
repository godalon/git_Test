package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.ScheduleDao;
import com.mypt.dto.ScheduleDto;

public class AdminScheduleListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ScheduleDao sdao = ScheduleDao.getInstance();
		ArrayList<ScheduleDto> arr=sdao.getScheduleList();
		
		request.setAttribute("arr", arr);

		return "admin/scheduleList";
	}

}
