package com.mypt.action.move;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.PboardDao;
import com.mypt.dto.PagingDto;
import com.mypt.dto.PboardDto;

public class MovePhotoAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("board", "pboard");
		String nick = session.getAttribute("nick").toString();
		PboardDao pdao = PboardDao.getInstance();
		int numPerPage = 9;

		// 검색 처리
		session.removeAttribute("keyField");
		session.removeAttribute("keyWord");

		// 페이징 처리
		int nowPage = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
		String next = request.getParameter("next");
		String prev = request.getParameter("prev");
		PagingDto page = new PagingDto(nowPage, pdao.getTotalCount(), numPerPage);
		if (next != null) {
			int nowBlock = Integer.parseInt(next) - 1;
			nowPage = page.getPagePerBlock() * nowBlock + 1;
			page = new PagingDto(nowPage, pdao.getTotalCount(), numPerPage);
		} else if (prev != null) {
			int nowBlock = Integer.parseInt(prev) - 1;
			nowPage = page.getPagePerBlock() * nowBlock + 5;
			page = new PagingDto(nowPage, pdao.getTotalCount(), numPerPage);
		}
		ArrayList<PboardDto> parr = pdao.getList(page.getStartPage(), page.getNumPerPage());
		ArrayList<Integer> comments = new ArrayList<Integer>();
		ArrayList<String> likes = new ArrayList<String>();

		for (int i = 0; i < parr.size(); i++) {
			int pb_num = parr.get(i).getNum();
			comments.add(pdao.commentNum(pb_num));
			likes.add(pdao.photoLikeCheck(pb_num, nick));
		}
		request.setAttribute("photoList", parr);
		request.setAttribute("comment", comments);
		request.setAttribute("likes", likes);

		request.setAttribute("totalPage", page.getTotalPage());
		request.setAttribute("nowPage", page.getNowPage());
		request.setAttribute("pageStart", page.getPageStart());
		request.setAttribute("pageEnd", page.getPageEnd());
		request.setAttribute("nowBlock", page.getNowBlock());
		request.setAttribute("totalBlock", page.getTotalBlock());

		return "common/photoBoard";
	}

}
