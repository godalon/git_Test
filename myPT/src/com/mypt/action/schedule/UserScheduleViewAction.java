package com.mypt.action.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mypt.controller.Action;
import com.mypt.dao.ProgramDao;
import com.mypt.dao.ScheduleDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.ProgramDto;
import com.mypt.dto.ScheduleDto;
import com.mypt.dto.UserDto;

public class UserScheduleViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("id");
		
		//오늘 날짜 및 해당 월 주차, 오늘 요일
		String today=request.getParameter("today");
		String[] weekDates=request.getParameterValues("weekDates[]");
		String[] arrDay = new String[5];
		for(int i=0;i<weekDates.length;i++) {
			arrDay[i]=today+weekDates[i];
			System.out.println(arrDay[i]);
		}
		// pt와 프로그램을 날짜별로 데이터를 받을 json
		JSONObject schdule=new JSONObject();
		// pt
		ScheduleDao sdao=ScheduleDao.getInstance();
		// 프로그램
		ProgramDao pdao=ProgramDao.getInstance();
		
		for(int i=0;i<arrDay.length;i++) {
			JSONObject jobj=new JSONObject();
			if(sdao.isScheduleExist(id, arrDay[i])==1) {
				ScheduleDto sdto=sdao.scheduleSelect(id, arrDay[i]);
				jobj.put("time", sdto.getS_time());
			}else if(pdao.isProgramExist(id, arrDay[i])==1){
				ProgramDto pdto=pdao.getProgram(id, arrDay[i]);
				jobj.put("part",pdto.getP_part());
				jobj.put("mention",pdto.getP_mention());
			}else {
				jobj.put("none","none");
			}
			schdule.put(i, jobj);
		}
		request.setAttribute("result", schdule);
		
		return "callback";
	}

}
