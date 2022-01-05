package com.mypt.action.detail;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;

public class AdminUserUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserDto dto = new UserDto();
		dto.setName(request.getParameter("userName"));
		dto.setGender(request.getParameter("gender"));
		dto.setBirth(request.getParameter("birthdate"));
		dto.setNick(request.getParameter("nickname"));
		dto.setEmail(request.getParameter("email"));
		dto.setPw(request.getParameter("password"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setTid(request.getParameter("trainer"));
		dto.setTel(request.getParameter("tel"));
		
		dto.setStartdate(request.getParameter("startDate"));
		dto.setEnddate(request.getParameter("endDate"));
		
		dto.setPtcount(Integer.parseInt(request.getParameter("remainNum")));
		dto.setId(request.getParameter("hiddenUserID"));
		dto.setAddrdetail(request.getParameter("addrdetail"));
		System.out.println(dto.getId());
		System.out.println(dto.getTid());
		System.out.println(dto.getPw());
		System.out.println(dto.getGender());
		System.out.println(dto.getName());
		
		
		UserDao dao = UserDao.getInstance();
		dao.manageUser(dto);
		
		
		return "redirect:userList.do";
	}

}
