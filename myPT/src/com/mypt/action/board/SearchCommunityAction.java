package com.mypt.action.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.CommentDao;
import com.mypt.dao.PboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.PagingDto;
import com.mypt.dto.PagingDto2;
import com.mypt.dto.PboardDto;

public class SearchCommunityAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("board", "cboard");
		String nick=session.getAttribute("nick").toString();
		CboardDao cdao=CboardDao.getInstance();
		
		//검색 처리
		String keyField;
		String keyWord;
		String head;
		if(request.getParameter("head")!=null) {
			head = request.getParameter("head");
			session.setAttribute("head", head);
		}else if(session.getAttribute("head")!=null&&!session.getAttribute("head").toString().equals("all")) {
			head = session.getAttribute("head").toString();
		}else {
			head = "all";
		}
		
		if(request.getParameter("keyWord")!=null) {
			System.out.println(request.getParameter("keyWord"));
			keyField=request.getParameter("keyField");
			keyWord=request.getParameter("keyWord");
			session.setAttribute("keyField", keyField);
			session.setAttribute("keyWord", keyWord);			
		}else {
			if(session.getAttribute("keyField")!=null) {
				keyField=session.getAttribute("keyField").toString();
			}else {
				keyField = "";
			}
			
			if(session.getAttribute("keyWord")!=null) {
				keyWord=session.getAttribute("keyWord").toString();
			}else {
				keyWord = "";
			}			
			
		}
		
		System.out.println("키필드"+keyField);
		System.out.println("키워드"+keyWord);
		//페이징 처리
		int totalRecord = cdao.getTotalCount(keyField, keyWord, head);
		int numPerPage = 10;
		int nowPage=Integer.parseInt(request.getParameter("page")==null?"1":request.getParameter("page"));
		String next=request.getParameter("next");
		String prev=request.getParameter("prev");
		PagingDto page=new PagingDto(nowPage, totalRecord, numPerPage);
		if(next!=null) {
			int nowBlock=Integer.parseInt(next)-1;
			nowPage=page.getPagePerBlock()*nowBlock+1;
			page=new PagingDto(nowPage, totalRecord, numPerPage);
		}else if(prev!=null){
			int nowBlock=Integer.parseInt(prev)-1;
			nowPage=page.getPagePerBlock()*nowBlock+5;
			page=new PagingDto(nowPage, totalRecord, numPerPage);
		}

		ArrayList<CboardDto> carr=cdao.getBoardList2(keyField,keyWord,page.getStartPage(), page.getNumPerPage(),head);
		ArrayList<Integer> comments=new ArrayList<Integer>();		
		
		CommentDao comdao = CommentDao.getInstance();
		for(int i=0;i<carr.size();i++) {
			int cb_num=carr.get(i).getNum();
			comments.add(comdao.countComment("ccomment", cb_num));
			
		}
		request.setAttribute("carr", carr);
		request.setAttribute("comment", comments);		
		
		request.setAttribute("totalPage", page.getTotalPage());
		request.setAttribute("nowPage", page.getNowPage());
		request.setAttribute("pageStart", page.getPageStart());
		request.setAttribute("pageEnd", page.getPageEnd());
		request.setAttribute("nowBlock", page.getNowBlock());
		request.setAttribute("totalBlock", page.getTotalBlock());
		
		
		return "common/community";
	}

}
