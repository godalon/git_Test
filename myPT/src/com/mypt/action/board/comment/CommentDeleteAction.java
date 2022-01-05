package com.mypt.action.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mypt.controller.Action;
import com.mypt.dao.CommentDao;
import com.mypt.dto.TestPagingDto;

public class CommentDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		String board = session.getAttribute("board").toString();
		
		int num= Integer.parseInt(request.getParameter("num"));		
		int c_num= Integer.parseInt(request.getParameter("c_num"));
		CommentDao cdao = CommentDao.getInstance();
		
		TestPagingDto paging= (TestPagingDto)session.getAttribute("paging");
		Integer deleteResult = 0;
		
		if(board.equals("cboard")) 
		{		
			deleteResult= cdao.commentDelete("ccomment", c_num);
			
		}
		else if(board.equals("pboard"))
		{
			deleteResult= cdao.commentDelete("pcomment", c_num);
		}
		else if(board.equals("qboard"))
		{
			deleteResult= cdao.commentDelete("qcomment", c_num);
		}
		
		paging.setTotalRecord(paging.getTotalRecord()-1);
		session.setAttribute("paging", paging);
		
		
		Gson gson = new Gson();
		JsonObject job= new JsonObject();
		
		
		job.add("paging", gson.fromJson(gson.toJson(paging, TestPagingDto.class), JsonObject.class));		
		job.addProperty("result", deleteResult);
		
	    response.setContentType("application/json; charset=utf-8");
		
		request.setAttribute("result", job);
		return "callback";
	}

}
