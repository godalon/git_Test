package com.mypt.action.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.ScheduleDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.ScheduleDto;
import com.mypt.dto.UserDto;

public class TrainerTodayPTAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String tid = session.getAttribute("id").toString();
		ArrayList<ScheduleDto> sarr = new ArrayList<ScheduleDto>();
		ScheduleDao sdao = ScheduleDao.getInstance();
		sarr = sdao.todayPTList(tid);
		
		ArrayList<UserDto> uarr = new ArrayList<UserDto>();
		UserDao udao = UserDao.getInstance();
		for(int i=0;i<sarr.size();i++) {
			String id = sarr.get(i).getS_id();
			UserDto ud = udao.getUserById(id);
			
			// 나이 계산
			SimpleDateFormat format = new SimpleDateFormat("yyyy");
			Date year = new Date();
			String thisYear = format.format(year);
			int age = (Integer.parseInt(thisYear.toString())
					- Integer.parseInt(ud.getBirth().substring(0, 4)) + 1);
			ud.setBirth(String.valueOf(age));
			
			uarr.add(ud);
		}
		
		request.setAttribute("uarr", uarr);
		request.setAttribute("sarr", sarr);
		
		return "trainer/trainerMain";
	}

}
