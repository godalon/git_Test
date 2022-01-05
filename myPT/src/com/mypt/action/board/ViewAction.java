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
import com.mypt.dao.LikeDao;
import com.mypt.dao.PboardDao;
import com.mypt.dao.QboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.PboardDto;
import com.mypt.dto.QboardDto;
import com.mypt.dto.TestPagingDto;

public class ViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));

		if(request.getParameter("b")!=null) {
			if(request.getParameter("b").toString().equals("c")) {
				session.setAttribute("board", "cboard");
			}else if(request.getParameter("b").toString().equals("q")){
				session.setAttribute("board", "qboard");
			}else if(request.getParameter("b").toString().equals("p")) {
				session.setAttribute("board", "pboard");
			}
		}
		String board = session.getAttribute("board").toString();
		String nick = session.getAttribute("nick").toString();
		CommentDao comdao = CommentDao.getInstance();

		
		///댓글 페이징
				int numPerPage=10;
				int pagePerBlock=5;		
				int totalRecord=0;
				int totalPage=0;
				int nowPage=0;
		
		if(board.equals("cboard")) {		

			CboardDto dto = new CboardDto();
			CboardDao dao = CboardDao.getInstance();
			LikeDao ldao = LikeDao.getInstance();
			int lflag = ldao.selectLike(nick, "cblike", num);
			dto = dao.detailView(num);

			///////////////////////////
			totalRecord = comdao.countComment("ccomment", num);

			totalPage = (int) Math.ceil((double) totalRecord / numPerPage); // 총 페이지 수
			nowPage = (totalRecord >= numPerPage) ? (int) Math.ceil((double) totalRecord / numPerPage) : 1; // 마지막 댓글페이지를 띄워줄
																																																			// 것

			TestPagingDto paging = new TestPagingDto(totalRecord, numPerPage, pagePerBlock, nowPage);

			// 한 페이지에 해당하는 댓글 가져오기
			JsonArray arr = comdao.getCommentsForOneCommentPage("ccomment", num, paging.getStartPage(), numPerPage);

			dto.setComments(arr);

			Gson gson = new Gson();
			gson.fromJson(gson.toJson(paging, TestPagingDto.class), JsonObject.class);

			//////////////////////

			int comment = comdao.countComment("ccomment", num);
			request.setAttribute("comment", comment);
			request.setAttribute("dto", dto);
			request.setAttribute("lflag", lflag);
			session.setAttribute("dto", dto);
			request.setAttribute("paging", paging);
			session.setAttribute("paging", paging);
		}

		else if (board.equals("qboard")) {
			QboardDto dto = new QboardDto();
			QboardDao dao = QboardDao.getInstance();
			dto = dao.detailView(num);

			///////////////////////////
			totalRecord = comdao.countComment("qcomment", num);

			totalPage = (int) Math.ceil((double) totalRecord / numPerPage); // 총 페이지 수
			nowPage = (totalRecord >= numPerPage) ? (int) Math.ceil((double) totalRecord / numPerPage) : 1; // 마지막 댓글페이지를 띄워줄
			// 것

			TestPagingDto paging = new TestPagingDto(totalRecord, numPerPage, pagePerBlock, nowPage);

			// 한 페이지에 해당하는 댓글 가져오기
			JsonArray arr = comdao.getCommentsForOneCommentPage("qcomment", num, paging.getStartPage(), numPerPage);

			dto.setComments(arr);

			Gson gson = new Gson();
			gson.fromJson(gson.toJson(paging, TestPagingDto.class), JsonObject.class);

			//////////////////////

			int comment = comdao.countComment("qcomment", num);
			request.setAttribute("comcnt", comment);
			request.setAttribute("dto", dto);
			session.setAttribute("dto", dto);
			request.setAttribute("paging", paging); ///
			session.setAttribute("paging", paging);
			
		} else if (board.equals("pboard")) {
			PboardDto dto = new PboardDto();
			PboardDao dao = PboardDao.getInstance();
			LikeDao ldao = LikeDao.getInstance();
			int lflag = ldao.selectLike(nick, "pblike", num);
			dto = dao.detailView(num);

			///////////////////////////
			totalRecord = comdao.countComment("pcomment", num);

			totalPage = (int) Math.ceil((double) totalRecord / numPerPage); // 총 페이지 수
			nowPage = (totalRecord >= numPerPage) ? (int) Math.ceil((double) totalRecord / numPerPage) : 1; // 마지막 댓글페이지를 띄워줄
			// 것

			TestPagingDto paging = new TestPagingDto(totalRecord, numPerPage, pagePerBlock, nowPage);

			// 한 페이지에 해당하는 댓글 가져오기
			JsonArray arr = comdao.getCommentsForOneCommentPage("pcomment", num, paging.getStartPage(), numPerPage);

			dto.setComments(arr);

			Gson gson = new Gson();
			gson.fromJson(gson.toJson(paging, TestPagingDto.class), JsonObject.class);

			//////////////////////

			int comment = dao.commentNum(num);
			request.setAttribute("comment", comment);
			request.setAttribute("dto", dto);
			request.setAttribute("lflag", lflag);
			session.setAttribute("dto", dto);
			request.setAttribute("paging", paging);////
			session.setAttribute("paging", paging);
		}

		return "common/boardDetail";
	}

}
