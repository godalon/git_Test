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

public class CommentChangePageAction implements Action 
{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
	
		TestPagingDto changedPage = (TestPagingDto)session.getAttribute("paging");
		int num = Integer.parseInt(request.getParameter("num"));
		String board = session.getAttribute("board").toString();		
		String nick = session.getAttribute("nick").toString();
			
		CommentDao cdao = CommentDao.getInstance();
		
		int nowPage=Integer.parseInt(request.getParameter("nowPage"));
		

		changedPage.setNowPage(nowPage);
		Gson gson= new Gson();
		JsonArray arr= null;
		
		if(board.equals("cboard"))
		{
			arr= cdao.getCommentsForOneCommentPage("ccomment", num, changedPage.getStartPage(), changedPage.getNumPerPage());	
		}
		else if(board.equals("pboard"))
		{
			arr= cdao.getCommentsForOneCommentPage("pcomment", num, changedPage.getStartPage(), changedPage.getNumPerPage());	
		}
		else if(board.equals("qboard"))
		{
			arr= cdao.getCommentsForOneCommentPage("qcomment", num, changedPage.getStartPage(), changedPage.getNumPerPage());	
		}
				
		JsonObject jsonData= new JsonObject();
		jsonData.add("paging", gson.fromJson(gson.toJson(changedPage, TestPagingDto.class), JsonObject.class));
		jsonData.add("comments", arr);	    
		
		session.setAttribute("paging", changedPage);
		
	    response.setContentType("application/json; charset=utf-8");
		request.setAttribute("result", jsonData);
		
		System.out.println(jsonData);
		
		
		return "callback";
	}
}

