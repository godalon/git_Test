package com.mypt.action.register;


import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;

public class AdminUserRegisterAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		UserDto dto= new UserDto();
		dto.setName(request.getParameter("userName"));
		dto.setGender(request.getParameter("gender"));

		dto.setBirth(request.getParameter("birthdate"));
		dto.setNick(request.getParameter("nickname"));
		dto.setEmail(request.getParameter("email"));
		dto.setPw(request.getParameter("password"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setQr(request.getParameter("qrcode"));
		
		
		String startDate= request.getParameter("startdate");
		String trainer = request.getParameter("trainer");
		
		if(startDate.equals(""))
		{
			startDate=null;
		}
		if(trainer.equals(""))
		{
			trainer= null;
		}
		
		dto.setStartdate(startDate);
		dto.setTid(trainer);
		dto.setTel(request.getParameter("tel"));
		
		dto.setAddrdetail(request.getParameter("addrdetail"));

		UserDao dao= UserDao.getInstance();		
		dao.insertUser(dto);
		
		return "redirect:userList.do";
	}

}
