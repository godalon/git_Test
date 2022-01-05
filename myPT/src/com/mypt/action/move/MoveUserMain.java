package com.mypt.action.move;


import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.TrainerDto;
import com.mypt.dto.UserDto;

public class MoveUserMain implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		UserDao dao=UserDao.getInstance();
		String id=(String)session.getAttribute("id");
		UserDto dto=dao.getUserById(id);
		int ptcount=dto.getPtcount();
		
		String startdate=dto.getStartdate();
		String enddate=dto.getEnddate();
		
		String tid=dto.getTid();
		TrainerDao tdao=TrainerDao.getInstance();
		TrainerDto tdto=tdao.trainerSelect(tid);
		String t_name=tdto.getT_name();
		String t_nick=tdto.getT_nick();
		
		
		request.setAttribute("ptcount", ptcount);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		
		request.setAttribute("t_name", t_name);
		request.setAttribute("t_nick", t_nick);
		return "user/userMain";
	}

}

