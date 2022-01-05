package com.mypt.action.move;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CommentDao;
import com.mypt.dao.LikeDao;
import com.mypt.dao.QboardDao;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.TrainerDto;
import com.mypt.dto.UserDto;

public class MoveTrainerProfileAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		session.setAttribute("nick", "길동이");
//		session.setAttribute("email", "hong@naver.com");
		String nick = session.getAttribute("nick").toString();
		String tid = session.getAttribute("id").toString();
		
		CommentDao cdao = CommentDao.getInstance();
		int ccnt = cdao.countUserComment(nick);
		
		LikeDao ldao = LikeDao.getInstance();
		int lcnt = ldao.userCountLike(nick);
		
		QboardDao bdao = QboardDao.getInstance();
		int bcnt = bdao.userBoardCount(nick);
		
		TrainerDao dao = TrainerDao.getInstance();
		TrainerDto dto = new TrainerDto();
		dto = dao.trainerSelect(tid);
		
		request.setAttribute("dto", dto);
		request.setAttribute("ccnt", ccnt);
		request.setAttribute("lcnt", lcnt);
		request.setAttribute("bcnt", bcnt);		
		
		return "trainer/trainerProfile";
	}

}
