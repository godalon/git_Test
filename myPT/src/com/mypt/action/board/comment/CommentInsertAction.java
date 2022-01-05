package com.mypt.action.board.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mypt.controller.Action;
import com.mypt.dao.CommentDao;
import com.mypt.dto.CommentDto;
import com.mypt.dto.TestPagingDto;

public class CommentInsertAction implements Action
{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		
		TestPagingDto paging= (TestPagingDto)session.getAttribute("paging");
		
		int num = Integer.parseInt(request.getParameter("num"));

		String board = session.getAttribute("board").toString();
		
		Integer insertResult = 0;
		
		Gson gson = new Gson();
		
		JsonArray arr= null;
		JsonObject result= new JsonObject();
		
		CommentDto comment = new CommentDto();			
		CommentDao cdao = CommentDao.getInstance();
		
		comment.setBoardNum(num);
		comment.setC_content(request.getParameter("c_content"));
		comment.setC_nick((String)session.getAttribute("nick"));
			
		int nowPage=Integer.parseInt(request.getParameter("nowPage"));
		
		
		if(board.equals("cboard"))
		{			
			if((nowPage!= paging.getTotalPage()) && paging.getTotalPage()!=0)
			{
				insertResult = cdao.commentInsert("ccomment", comment);				
				paging.setTotalRecord(paging.getTotalRecord()+1);
				paging.setNowPage(paging.getTotalPage());					
				arr = cdao.getCommentsForOneCommentPage("ccomment", num, paging.getStartPage(), paging.getNumPerPage());			
				result.add("comments", arr);							
			}
			else
			{
				insertResult = cdao.commentInsert("ccomment", comment);
				paging.setTotalRecord(paging.getTotalRecord()+1);
				
				if(insertResult==1)
				{
					comment = cdao.getLastComment("ccomment", num);					
				}
				
					result.add("comments", gson.fromJson(gson.toJson(comment, CommentDto.class), JsonObject.class));
			}					
		}	
		
		else if(board.equals("pboard"))
		{	

			if(nowPage!= paging.getTotalPage())
			{
				insertResult = cdao.commentInsert("pcomment", comment);
				paging.setTotalRecord(paging.getTotalRecord()+1);
				paging.setNowPage(paging.getTotalPage());					
				arr = cdao.getCommentsForOneCommentPage("pcomment", num, paging.getStartPage(), paging.getNumPerPage());				
				
				result.addProperty("insertResult", gson.toJson(insertResult));
				result.add("comments", arr);
				
			}
			else
			{
				insertResult = cdao.commentInsert("pcomment", comment);
				paging.setTotalRecord(paging.getTotalRecord()+1);
				
				if(insertResult==1)
				{
					comment = cdao.getLastComment("pcomment", num);					
				}
				
				result.add("comments", gson.fromJson(gson.toJson(comment, CommentDto.class), JsonObject.class));
				
			}		
				
		}
		
		else if(board.equals("qboard"))
		{	

			if(nowPage!= paging.getTotalPage())
			{
				insertResult = cdao.commentInsert("qcomment", comment);
				paging.setTotalRecord(paging.getTotalRecord()+1);
				paging.setNowPage(paging.getTotalPage());					
				arr = cdao.getCommentsForOneCommentPage("qcomment", num, paging.getStartPage(), paging.getNumPerPage());				
				
				result.addProperty("insertResult", gson.toJson(insertResult));
				result.add("comments", arr);
				
			}
			else
			{
				insertResult = cdao.commentInsert("qcomment", comment);
				paging.setTotalRecord(paging.getTotalRecord()+1);
				
				if(insertResult==1)
				{
					comment = cdao.getLastComment("qcomment", num);					
				}
				
				result.add("comments", gson.fromJson(gson.toJson(comment, CommentDto.class), JsonObject.class));
				
			}		
				
		}
		
		session.setAttribute("paging", paging);	
		result.add("paging", gson.fromJson(gson.toJson(paging, TestPagingDto.class), JsonObject.class));
		result.addProperty("result", insertResult);
		request.setAttribute("result", result);	
		
	    response.setContentType("application/json; charset=utf-8");

		
		return "callback";
	}


}
