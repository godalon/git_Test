package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.CommentDao;
import com.mypt.dao.PboardDao;
import com.mypt.dao.QboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.CommentDto;
import com.mypt.dto.PboardDto;
import com.mypt.dto.QboardDto;

public class UserCommentListViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		session.setAttribute("nick", "길동이");
		String nick = session.getAttribute("nick").toString();
		CommentDao comdao = CommentDao.getInstance();
		
		
		ArrayList<CommentDto> ccom = new ArrayList<CommentDto>();								
		ccom = comdao.usercommentList("ccomment", nick);	
		
		ArrayList<CommentDto> qcom = new ArrayList<CommentDto>();								
		qcom = comdao.usercommentList("qcomment", nick);
		
		ArrayList<CommentDto> pcom = new ArrayList<CommentDto>();								
		pcom = comdao.usercommentList("pcomment", nick);
		
		request.setAttribute("ccom", ccom);
		request.setAttribute("qcom", qcom);
		request.setAttribute("pcom", pcom);
		
		return "user/userCommentList";
	}

}
