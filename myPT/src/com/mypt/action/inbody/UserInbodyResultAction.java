package com.mypt.action.inbody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.mypt.controller.Action;
import com.mypt.dao.InbodyDao;

public class UserInbodyResultAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		
		String id= (String) session.getAttribute("id");
		String userName= (String) session.getAttribute("name");

		InbodyDao dao = InbodyDao.getInstance();
                
	    JsonArray jarr = dao.getUserInbodyList(id);
	    
	    response.setContentType("application/json; charset=utf-8");
		request.setAttribute("result", jarr);
		
		return "callback";
	}

}
