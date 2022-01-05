package com.mypt.action.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mypt.controller.Action;
import com.mypt.dao.CommentDao;
import com.mypt.dto.CommentDto;
import com.mypt.dto.TestPagingDto;

public class CommentUpdateAction implements Action
{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String board = session.getAttribute("board").toString();
		
		int updateResult= 0;
		
		CommentDto comment = new CommentDto();			
		CommentDao cdao = CommentDao.getInstance();
		
		comment.setC_num(Integer.parseInt(request.getParameter("c_num")));
		comment.setC_content(request.getParameter("c_content"));
							
		if(board.equals("cboard"))
		{
			updateResult = cdao.commentUpdate("ccomment", comment);					
		}
		else if(board.equals("pboard"))
		{
			updateResult = cdao.commentUpdate("pcomment", comment);					
		}
		else if(board.equals("qboard"))
		{
			updateResult = cdao.commentUpdate("qcomment", comment);					
		}
		
		request.setAttribute("result", updateResult);				

		
		
		return "callback";
	}

}
