package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.HistoryDao;
import com.mypt.dto.HistoryDto;

public class AdminHistoryListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HistoryDao hdao = HistoryDao.getInstance();		
		ArrayList<HistoryDto> arr = hdao.getHistoryList();
		
		request.setAttribute("arr", arr);
		
		
		return "admin/historyList";
	}

}
