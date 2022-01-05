package com.mypt.action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dto.TrainerDto;

public class AdminTrainerDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String t_id = request.getParameter("t_id");
		TrainerDao tdao = TrainerDao.getInstance();
		TrainerDto trainer = tdao.trainerSelect(t_id);
		request.setAttribute("t", trainer);		
		return "admin/trainerDetail";
	}
	
	

}
