package com.mypt.action.move;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.TrainerDto;

public class MoveUserRegister implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
//		트레이너 정보
		TrainerDao dao = TrainerDao.getInstance();
		ArrayList<TrainerDto> arr = dao.trainerList();
		
		request.setAttribute("result", arr);
		
		return "admin/userRegister";
	}

}
