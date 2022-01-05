package com.mypt.action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.TrainerDto;
import com.mypt.dto.UserDto;

public class TrainerMyUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TrainerDto dto = new TrainerDto();
		dto.setT_id(request.getParameter("hiddentid"));
		dto.setT_pw(request.getParameter("password"));
		dto.setT_zipcode(request.getParameter("zipcode"));
		dto.setT_address(request.getParameter("address"));
		dto.setT_email(request.getParameter("email"));
		dto.setT_tel(request.getParameter("tel"));
		dto.setT_addrdetail(request.getParameter("addrdetail"));
		
		TrainerDao dao = TrainerDao.getInstance();
		dao.trainerMyUpdate(dto);
		
		return "redirect:moveTrainerProfile.do";
	}

}
