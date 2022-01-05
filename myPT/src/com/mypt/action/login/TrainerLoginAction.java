package com.mypt.action.login;

import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.AdminDao;
import com.mypt.dao.TrainerDao;
import com.mypt.dto.AdminDto;
import com.mypt.dto.TrainerDto;


public class TrainerLoginAction implements Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();	
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int flag = 0;
		
		String pwRemember= request.getParameter("pwRemember");

		int cookieMaxAge = 60*60*24*14;		
		String sessionId = "";
		Cookie cookie = null;
		Timestamp cookieEnd= null;
		
		
		if(id.equals("admin")) {
			AdminDao dao = AdminDao.getInstance();
			flag = dao.adminLogin(id, pw);
			if(flag==2) 
			{
				 AdminDto dto = dao.getAdmin(id);	
				 
				 session.setAttribute("nick", "관리자");
				 session.setAttribute("grade", 0);
				 
				 if(pwRemember!=null && pwRemember.equals("on"))
				 {				
					 sessionId= session.getId();	 
					 cookie= new Cookie("myPTLoginCookie", sessionId);
					 
					 cookie.setPath("/"); 
					 cookie.setMaxAge(cookieMaxAge); 
					 response.addCookie(cookie);
					 
					 cookieEnd = new Timestamp(System.currentTimeMillis()+ (1000*cookieMaxAge));
					 
					 dao.keepLogin(sessionId, cookieEnd);			 
				 }
				 
			}			
			
		}
		else 
		{
			TrainerDao dao = TrainerDao.getInstance();
			flag = dao.trainerLogin(id, pw);
			
			if(flag==1) 
			{
			 TrainerDto trainer = dao.trainerSelect(id);			 
			 session.setAttribute("name", trainer.getT_name());
			 session.setAttribute("nick", trainer.getT_nick());
			 session.setAttribute("id", trainer.getT_id());
			 session.setAttribute("grade", 1);
			 
				 if(pwRemember!=null && pwRemember.equals("on"))
				 {				
					 sessionId= session.getId();	 
					 cookie= new Cookie("myPTLoginCookie", sessionId);
					 
					 cookie.setPath("/"); 
					 cookie.setMaxAge(cookieMaxAge); 
					 response.addCookie(cookie);
					 
					 cookieEnd = new Timestamp(System.currentTimeMillis()+ (1000*cookieMaxAge));
					 
					 dao.keepLogin(trainer.getT_id(), sessionId, cookieEnd);			 
				 }
			 
			}
			
		}
		
			
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("result", flag);
		
		
		return "callback";
	}
	
	

}
