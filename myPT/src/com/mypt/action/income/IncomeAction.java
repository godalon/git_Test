package com.mypt.action.income;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mypt.controller.Action;
import com.mypt.dao.HistoryDao;
import com.mypt.dto.HistoryDto;

public class IncomeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		System.out.println(year);
		HistoryDao dao = HistoryDao.getInstance();
		ArrayList<HistoryDto> arr = dao.getincome(year,month);
		for(HistoryDto dto: arr) {
			System.out.println(dto.getMonth());
			System.out.println(dto.getIncome());
			System.out.println(dto.getUsercnt());
		}
		
		JSONArray jarr = new JSONArray();
		for(HistoryDto dto: arr) {
			JSONObject obj = new JSONObject();
			obj.put("month", dto.getMonth());
			obj.put("income", dto.getIncome());
			obj.put("user", dto.getUsercnt());
			jarr.add(obj);
		}
		request.setAttribute("result", jarr);
		
		
		
		return "callback";
		
		
	}

}
