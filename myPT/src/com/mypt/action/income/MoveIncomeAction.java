package com.mypt.action.income;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.HistoryDao;

public class MoveIncomeAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		HistoryDao dao = HistoryDao.getInstance();
		
		// 현재, 이전 달 수입
		int nowIncome = dao.monthIncome(year, month); 
		int prevIncome = dao.monthIncome(year, month-1);
		
		
		//현재 회원수
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf1.format(cal.getTime());
		int usercnt = dao.nowUser(nowDate);
		
		//신규 회원수
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
		nowDate = sdf2.format(cal.getTime());
		int newuser = dao.newUser(nowDate);
		
		request.setAttribute("nowIncome", nowIncome);
		if(nowIncome-prevIncome>0) {
			String var = "+"+ (nowIncome-prevIncome);
			request.setAttribute("var", var);
			}
		else request.setAttribute("var", nowIncome-prevIncome);
		request.setAttribute("nowUser", usercnt);
		request.setAttribute("newUser", newuser);		
		
		return "/admin/income";
	}

}

