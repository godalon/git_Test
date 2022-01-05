package com.mypt.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.QboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.QboardDto;

public class ReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String board = session.getAttribute("board").toString();
		//커뮤니티 
		if(board.equals("cboard")) {
			CboardDto dto = new CboardDto();
					
			dto.setWriter(session.getAttribute("nick").toString());
			if(dto.getWriter()==""|dto.getWriter()==null) {
				dto.setWriter("길동이");
			}
			
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setRef(Integer.parseInt(request.getParameter("ref")));
			dto.setDepth(Integer.parseInt(request.getParameter("depth")));
			dto.setPos(Integer.parseInt(request.getParameter("pos")));
			
			CboardDao dao = CboardDao.getInstance();
			dao.replyBoard(dto);
			dao.replyUpBoard(dto.getRef(), dto.getPos());
			
			
			return "redirect:moveCommunity.do";
			
			// 다른 게시판 write
		}else if(board.equals("qboard")) {
			QboardDto dto = new QboardDto();
					
			dto.setWriter(session.getAttribute("nick").toString());
			if(dto.getWriter()==""|dto.getWriter()==null) {
				dto.setWriter("길동이");
			}
			
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setRef(Integer.parseInt(request.getParameter("ref")));
			dto.setDepth(Integer.parseInt(request.getParameter("depth")));
			dto.setPos(Integer.parseInt(request.getParameter("pos")));
			
			QboardDao dao = QboardDao.getInstance();
			dao.replyBoard(dto);
			dao.replyUpBoard(dto.getRef(), dto.getPos());
			
			
			return "redirect:moveQuestion.do";
			
			// 다른 게시판 write
		}
		else {
			return null;
		}
	}

}
