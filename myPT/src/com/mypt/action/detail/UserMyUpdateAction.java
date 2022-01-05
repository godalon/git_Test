package com.mypt.action.detail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;

public class UserMyUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		UserDto dto = new UserDto();
		dto.setEmail(request.getParameter("hiddenemail"));
		dto.setPw(request.getParameter("password"));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setTel(request.getParameter("tel"));
		dto.setAddrdetail(request.getParameter("addrdetail"));
		
		UserDao dao = UserDao.getInstance();
		dao.updateUser(dto);
		
		return "redirect:moveUserProfile.do";
	}

}
