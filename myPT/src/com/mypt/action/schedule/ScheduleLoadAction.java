package com.mypt.action.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.mypt.controller.Action;
import com.mypt.dao.ProgramDao;
import com.mypt.dao.ScheduleDao;
import com.mypt.dto.ProgramDto;
import com.mypt.dto.ScheduleDto;

public class ScheduleLoadAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("uid");
		String[] date = request.getParameterValues("alldate[]");

		// pt와 프로그램을 날짜별로 데이터를 받을 json
		JSONObject schdule = new JSONObject();
		// pt
		ScheduleDao sdao = ScheduleDao.getInstance();
		// 프로그램
		ProgramDao pdao = ProgramDao.getInstance();

		for (int i = 0; i < date.length; i++) {
			JSONObject jobj = new JSONObject();
			if (sdao.isScheduleExist(id, date[i]) == 1) {
				ScheduleDto sdto = sdao.scheduleSelect(id, date[i]);
				jobj.put("time", sdto.getS_time());
			} else if (pdao.isProgramExist(id, date[i]) == 1) {
				ProgramDto pdto = pdao.getProgram(id, date[i]);
				jobj.put("part", pdto.getP_part());
				jobj.put("mention", pdto.getP_mention());
			} else {
				jobj.put("none", "none");
			}
			schdule.put(i+1, jobj);
		}
		
		request.setAttribute("result", schdule);
		
		return "callback";
	}

}
