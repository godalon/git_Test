package com.mypt.action.schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.ProgramDao;
import com.mypt.dao.ScheduleDao;
import com.mypt.dto.ProgramDto;
import com.mypt.dto.ScheduleDto;

public class TrainerScheduleSettingAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("uid");
		ProgramDao pdao=ProgramDao.getInstance();
		ScheduleDao sdao=ScheduleDao.getInstance();
		
		String[] pt=request.getParameterValues("time[]");
		String[] program=request.getParameterValues("program[]");
		String[] proMention=request.getParameterValues("proMention[]");	
		String[] date=request.getParameterValues("date[]");
		
		for(int i=0;i<date.length;i++) {
			if(pt[i].equals("")&&!program[i].equals("")) {
				ProgramDto pdto=new ProgramDto();
				pdto.setP_id(id);
				pdto.setP_date(date[i]);
				pdto.setP_mention(proMention[i]);
				pdto.setP_part(program[i]);
				
				//해당 날짜에 스케줄이 있으면 삭제 후 저장
				if(sdao.isScheduleExist(id, date[i])==1) {
					sdao.ScheduleDelete(id, date[i]);
				}
				
				if(pdao.isProgramExist(id, date[i])==1) {
					//업데이트
					pdao.updateProgram(pdto);
				}else {
					pdao.insertProgram(pdto);
				}
			}else if(program[i].equals("")&&!pt[i].equals("")){
				ScheduleDto sdto=new ScheduleDto();
				sdto.setS_id(id);
				sdto.setS_date(date[i]);
				sdto.setS_time(pt[i]);
				
				//해당 날짜에 프로그램이 있으면 삭제 후 저장
				if(pdao.isProgramExist(id, date[i])==1) {
					pdao.deleteProgram(id, date[i]);
				}
				
				if(sdao.isScheduleExist(id, date[i])==1) {
					//업데이트
					sdao.ScheduleUpdate(sdto);
				}else {
					sdao.scheduleInsert(sdto);
				}
				
			}else {
				System.out.println("둘다 비어 삭제함");
				if(pdao.isProgramExist(id, date[i])==1) {
					pdao.deleteProgram(id, date[i]);
				}else if(sdao.isScheduleExist(id, date[i])==1) {
					sdao.ScheduleDelete(id, date[i]);
				}
			}
		}
		
		return "callback";
	}

}
