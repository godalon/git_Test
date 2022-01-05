package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.HeadCountDao;
import com.mypt.dao.HistoryDao;
import com.mypt.dto.HeadCountDto;
import com.mypt.dto.HistoryDto;

public class AdminHeadCountListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HeadCountDao hdao = HeadCountDao.getInstance();	
		ArrayList<HeadCountDto> arr = hdao.headcountList();
		
		request.setAttribute("arr", arr);
		
		
		return "admin/headcountList";
	}

}
