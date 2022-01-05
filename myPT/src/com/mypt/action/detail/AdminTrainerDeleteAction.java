package com.mypt.action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;

public class AdminTrainerDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String t_id = request.getParameter("t_id");
		TrainerDao tdao = TrainerDao.getInstance();
		tdao.trainerDelete(t_id);
		
		return "redirect:trainerList.do";
	}

}
