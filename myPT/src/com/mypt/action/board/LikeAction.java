package com.mypt.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.LikeDao;
import com.mypt.dto.LikeDto;

public class LikeAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(request.getParameter("num"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		HttpSession session = request.getSession();
		String board = session.getAttribute("board").toString();
		String lboard= "";		
		String nick = session.getAttribute("nick").toString();
//		String nick = "길동이";
		if(board.equals("cboard")) {
			lboard = "cblike";
		}else {
			lboard = "pblike";
		}
		
		LikeDto dto = new LikeDto(); 
		dto.setNum(num);
		dto.setNick(nick);
		LikeDao dao = LikeDao.getInstance();
		if(flag==0) {
			dao.delete(dto, lboard, board);
		} else {
			dao.insert(dto, lboard, board);			
		}			
		
		return "callback";
	}

}
