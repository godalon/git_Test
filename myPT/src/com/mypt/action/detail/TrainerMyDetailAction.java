package com.mypt.action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.TrainerDto;
import com.mypt.dto.UserDto;

public class TrainerMyDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String tid = session.getAttribute("id").toString();
		TrainerDao dao = TrainerDao.getInstance();
		TrainerDto dto = new TrainerDto();
		dto = dao.trainerSelect(tid);
		
		request.setAttribute("dto", dto);
		
		return "trainer/trainerUpdate";
	}
	
	

}
