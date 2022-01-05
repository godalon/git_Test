package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.CommentDao;
import com.mypt.dao.LikeDao;
import com.mypt.dao.PboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.PboardDto;

public class UserLikeListViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		session.setAttribute("nick", "길동이");
		String nick = session.getAttribute("nick").toString();		
		LikeDao ldao = LikeDao.getInstance();
		CommentDao comdao = CommentDao.getInstance();
		
		ArrayList<Integer> clike = new ArrayList<Integer>();
		clike = ldao.likeNum(nick, "cblike");		
		CboardDao cdao = CboardDao.getInstance();		
		ArrayList<CboardDto> carr = new ArrayList<CboardDto>();
		ArrayList<Integer> ccom = new ArrayList<Integer>();
		for(int i=0; i<clike.size();i++) {
			int cnum = clike.get(i);						
			carr.add(cdao.detailView(cnum));
			ccom.add(comdao.countComment("ccomment", cnum));
		}
				
		ArrayList<Integer> plike = new ArrayList<Integer>();
		plike = ldao.likeNum(nick, "pblike");		
		PboardDao pdao = PboardDao.getInstance();		
		ArrayList<PboardDto> parr = new ArrayList<PboardDto>();
		ArrayList<Integer> pcom = new ArrayList<Integer>();
		for(int i=0; i<plike.size();i++) {
			int pnum = plike.get(i);						
			parr.add(pdao.detailView(pnum));
			pcom.add(comdao.countComment("pcomment", pnum));
		}
		
		request.setAttribute("carr", carr);	
		request.setAttribute("ccom", ccom);	
		request.setAttribute("parr", parr);		
		request.setAttribute("pcom", pcom);		
		
		return "user/userLikeList";
	}

}
