package com.mypt.action.schedule;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.ScheduleDao;
import com.mypt.dao.UserDao;

public class TrainerPTFinishAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserDao dao = UserDao.getInstance();
		ScheduleDao sdao = ScheduleDao.getInstance();
		String[] arr = request.getParameterValues("id");		
		
		for(int i=0; i<arr.length;i++) {
			dao.ptFinish(arr[i]);
			sdao.ptFinish(arr[i]);			
		}
		
		return "redirect:trainerMain.do";
	}

}
