package com.mypt.action.register;


import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.TrainerDto;
import com.mypt.dto.UserDto;

public class AdminTrainerRegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		TrainerDao dao= TrainerDao.getInstance();		
		dao.insertTrainer(request);
		
		return "redirect:trainerList.do";
	}

}
