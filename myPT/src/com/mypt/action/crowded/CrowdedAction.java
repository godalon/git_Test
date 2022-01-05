package com.mypt.action.crowded;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.HeadCountDao;

public class CrowdedAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String date = request.getParameter("today");
		HeadCountDao dao = HeadCountDao.getInstance();
		int num = dao.crowdedCheck(date);
		int result = 0;
		
		if(num<=10) {
			result = 0;
		}else if(num < 20) {
			result = 1;
		}else {
			result = 2;
		}
		
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("result", result);				
		
		return "callback";
	}

}
