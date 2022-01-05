package com.mypt.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.CommentDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.TestPagingDto;

public class TestViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		String board = session.getAttribute("board").toString();		
		String nick = session.getAttribute("nick").toString();
		
		CommentDao cdao = CommentDao.getInstance();		
		
		///댓글 페이징
		int numPerPage=20;
		int pagePerBlock=5;		
		int totalRecord=0;
		int totalPage=0;
		int nowPage=0;
		
		if(board.equals("cboard")) 
		{		
			CboardDao dao = CboardDao.getInstance();
			CboardDto dto = dao.detailView(num);
					
			totalRecord = cdao.countComment("ccomment", num);
					
			totalPage= (int)Math.ceil((double)totalRecord/numPerPage); //총 페이지 수
			nowPage= (totalRecord>=numPerPage) ? (int)Math.ceil((double)totalRecord/numPerPage) : 1; //마지막 댓글페이지를 띄워줄 것
			
			TestPagingDto paging= new TestPagingDto(totalRecord, numPerPage, pagePerBlock, nowPage);
			
			 //	한 페이지에 해당하는 댓글 가져오기  
			JsonArray arr = cdao.getCommentsForOneCommentPage("ccomment", num, paging.getStartPage(), numPerPage);
			
			dto.setComments(arr); 
			session.setAttribute("dto", dto);
									
			
			Gson gson = new Gson();
			gson.fromJson(gson.toJson(paging, TestPagingDto.class), JsonObject.class);
			
			request.setAttribute("dto", dto);
			
			request.setAttribute("paging", paging);
			session.setAttribute("paging", paging);
			
			request.setAttribute("num", num);

					
		}
		else if(board.equals("pboard"))
		{
			
		}

		
		return "common/testBoardDetail";
		

	}

}
