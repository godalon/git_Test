package com.mypt.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.PboardDao;
import com.mypt.dao.QboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.PboardDto;
import com.mypt.dto.QboardDto;

public class UpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// 커뮤니티 게시판
		if (session.getAttribute("board").equals("cboard")) {
			CboardDto dto = new CboardDto();
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setHead(request.getParameter("writeHead"));
			CboardDao dao = CboardDao.getInstance();
			dao.update(dto);
			return "redirect: moveCommunity.do";
			// 포토 게시판
		} else if (session.getAttribute("board").equals("pboard")) {
			PboardDto dto = new PboardDto();
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));

			String content = dto.getContent();
			System.out.println(content);
			String target = "<img";
			String targetEnd = ">";
			int targetNum = content.indexOf(target);
			System.out.println(targetNum);
			String photo = content.substring(targetNum, content.substring(targetNum).indexOf(targetEnd) + targetNum)
					+ "class=\"card-img-top mb-1\" />";
			dto.setPhoto(photo);

			PboardDao dao = PboardDao.getInstance();
			dao.update(dto);
			return "redirect: movePhoto.do";
			// 질문 게시판
		} else {
			QboardDto dto = new QboardDto();
			dto.setNum(Integer.parseInt(request.getParameter("num")));
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			QboardDao dao = QboardDao.getInstance();
			dao.update(dto);
			return "redirect: moveQuestion.do";

		}
	}

}
