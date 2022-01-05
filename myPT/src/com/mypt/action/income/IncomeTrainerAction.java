package com.mypt.action.income;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mypt.controller.Action;
import com.mypt.dao.HistoryDao;
import com.mypt.dao.TrainerDao;
import com.mypt.dto.HistoryDto;

public class IncomeTrainerAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		request.setCharacterEncoding("utf-8");
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		System.out.println(year);
		HistoryDao dao = HistoryDao.getInstance();
		ArrayList<HistoryDto> arr = dao.getTrainerIncome(year,month);
		ArrayList<String> photo = new ArrayList<String>();
		
		TrainerDao tdao = TrainerDao.getInstance();
		for(int i=0; i<arr.size();i++) {
			String tid = arr.get(i).getTid();
			System.out.println(tid);
			String ph = tdao.trainerSelect(tid).getT_photo(); 
			photo.add(ph);
			System.out.println(ph);
		}
				
		
		JSONArray jarr = new JSONArray();
		for(int i=0;i<arr.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("tid", arr.get(i).getTid());
			obj.put("income", arr.get(i).getIncome());
			obj.put("tname", arr.get(i).getT_name());
			obj.put("tphoto", photo.get(i));
			jarr.add(obj);
		}
		request.setAttribute("result", jarr);
		
		
		return "callback";
		
		
	}

}
