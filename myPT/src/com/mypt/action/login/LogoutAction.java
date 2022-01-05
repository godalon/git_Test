package com.mypt.action.login;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.AdminDao;
import com.mypt.dao.TrainerDao;
import com.mypt.dao.UserDao;

public class LogoutAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		Cookie cookies[] = request.getCookies();
		Cookie cookie=null;
		
		 if(cookies!=null)
		    {		         
		        for(Cookie c: cookies)
		        {
		        	if(c.getName().equals("myPTLoginCookie")) //로그인 용 쿠키 찾으면
		        	{
		        		cookie= c;
	        			 cookie.setMaxAge(0); //없애버림
	        			 response.addCookie(cookie);
	        			 Timestamp expired= new Timestamp(System.currentTimeMillis());        			 	        			 

	        			 if(session.getAttribute("grade").equals(2))
	        			 {
	        				 UserDao dao= UserDao.getInstance();
		        			 dao.keepLogin(session.getAttribute("id").toString(), "", expired); //쿠키 만료 업데이트
	        			 }
	        			 
	        			 else if(session.getAttribute("grade").equals(1))//트레이너
	        			 {
	        				 TrainerDao tdao = TrainerDao.getInstance();
	        				 tdao.keepLogin(session.getAttribute("id").toString(), "", expired);
	        			 }
	        			 
	        			 else//관리자
	        			 {
	        				 AdminDao adao = AdminDao.getInstance();
	        				 adao.keepLogin("", expired);	        				
	        			 }
	        			 
	        			 break;
		        		
		        	}
		        }
		    }
		 
				
			session.invalidate();
			
		
		return "redirect:intro.jsp";
	}

}
