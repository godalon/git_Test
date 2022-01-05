package com.mypt.action.move;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.CommentDao;
import com.mypt.dao.QboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.PagingDto;
import com.mypt.dto.QboardDto;

public class MoveQuestionAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("board", "qboard");
		session.removeAttribute("dto");
		
		String nick = session.getAttribute("nick").toString();
		QboardDao qdao = QboardDao.getInstance();

		// 검색 처리
		session.removeAttribute("keyField");
		session.removeAttribute("keyWord");
		session.removeAttribute("dto");
		session.setAttribute("head", "all");
		String head = session.getAttribute("head").toString();

		// 페이징 처리
		int totalRecord = qdao.getTotalCount();
		int numPerPage = 10;
		int nowPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		System.out.println("페이지?"+nowPage);
		String next = request.getParameter("next");
		String prev = request.getParameter("prev");
		PagingDto page = new PagingDto(nowPage,totalRecord,numPerPage);
		if (next != null) {
			int nowBlock = Integer.parseInt(next) - 1;
			nowPage = page.getPagePerBlock() * nowBlock + 1;
			page = new PagingDto(nowPage,totalRecord,numPerPage);
		} else if (prev != null) {
			int nowBlock = Integer.parseInt(prev) - 1;
			nowPage = page.getPagePerBlock() * nowBlock + 5;
			page = new PagingDto(nowPage,totalRecord,numPerPage);
		}
		String keyword = "";
		String keyfield = "";
		ArrayList<QboardDto> qarr = qdao.getBoardList(keyword,keyfield,page.getStartPage(), page.getNumPerPage());
		ArrayList<Integer> comments = new ArrayList<Integer>();		

		CommentDao comdao = CommentDao.getInstance();
		for (int i = 0; i < qarr.size(); i++) {
			int qb_num = qarr.get(i).getNum();
			comments.add(comdao.countComment("qcomment", qb_num));			
		}
		request.setAttribute("qarr", qarr);
		request.setAttribute("comment", comments);
		request.setAttribute("totalPage", page.getTotalPage());
		request.setAttribute("nowPage", page.getNowPage());
		request.setAttribute("pageStart", page.getPageStart());
		request.setAttribute("pageEnd", page.getPageEnd());
		request.setAttribute("nowBlock", page.getNowBlock());
		request.setAttribute("totalBlock", page.getTotalBlock());
		
		
		return "common/question";
	}

}
