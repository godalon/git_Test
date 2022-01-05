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
import com.mypt.dto.PboardDto;
import com.mypt.dto.QboardDto;

public class UserBoardListViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		session.setAttribute("nick", "길동이");
		String nick = session.getAttribute("nick").toString();
		CommentDao comdao = CommentDao.getInstance();
		
		CboardDao cdao = CboardDao.getInstance();		
		ArrayList<CboardDto> carr = new ArrayList<CboardDto>();
		carr = cdao.userList(nick);
		ArrayList<Integer> ccom = new ArrayList<Integer>();
		for(int i=0; i<carr.size();i++) {
			int cnum = carr.get(i).getNum();						
			ccom.add(comdao.countComment("ccomment", cnum));
		}
		
		
		QboardDao qdao = QboardDao.getInstance();		
		ArrayList<QboardDto> qarr = new ArrayList<QboardDto>(); 
		qarr = qdao.userList(nick);
		ArrayList<Integer> qcom = new ArrayList<Integer>();	
		for(int i=0; i<qarr.size();i++) {
			int qnum = qarr.get(i).getNum();					
			qcom.add(comdao.countComment("qcomment", qnum));
		}
		
		PboardDao pdao = PboardDao.getInstance();		
		ArrayList<PboardDto> parr = new ArrayList<PboardDto>(); 
		parr = pdao.userList(nick);
		ArrayList<Integer> pcom = new ArrayList<Integer>();
		for(int i=0; i<parr.size();i++) {
			int pnum = parr.get(i).getNum();						
			pcom.add(comdao.countComment("pcomment", pnum));
		}
		
		request.setAttribute("carr", carr);
		request.setAttribute("qarr", qarr);
		request.setAttribute("parr", parr);
		request.setAttribute("ccom", ccom);
		request.setAttribute("qcom", qcom);
		request.setAttribute("pcom", pcom);
		
		return "user/userBoardList";
	}

}
