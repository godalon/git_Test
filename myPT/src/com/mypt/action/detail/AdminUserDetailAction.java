package com.mypt.action.detail;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.TrainerDto;
import com.mypt.dto.UserDto;

public class AdminUserDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		UserDao udao = UserDao.getInstance();
		UserDto user = udao.getUserById(id);
		

		
		
		TrainerDao tdao = TrainerDao.getInstance();
		ArrayList<TrainerDto> arr = tdao.trainerList();
		request.setAttribute("user", user);
		request.setAttribute("arr", arr);
		return "admin/userDetail";
	}
	
	

}
