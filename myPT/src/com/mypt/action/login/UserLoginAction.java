package com.mypt.action.login;


import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;

public class UserLoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		UserDao dao = UserDao.getInstance();
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		String pwRemember= request.getParameter("pwRemember");
		
		int flag = dao.userLogin(email, pw);
		System.out.println(flag);
		if(flag==1) {
		 UserDto user = dao.getUserByEmail(email);		
		 HttpSession session = request.getSession();
		 session.setAttribute("id", user.getId());
		 session.setAttribute("name", user.getName());
		 session.setAttribute("nick", user.getNick());		
		 session.setAttribute("email", user.getEmail());
		 session.setAttribute("grade", 2);
		 
			 if(pwRemember!=null && pwRemember.equals("on"))
			 {
				 int cookieMaxAge = 60*60*24*14;
				 String sessionId= session.getId();
				 
				 Cookie cookie= new Cookie("myPTLoginCookie", sessionId);
				 
				 cookie.setPath("/"); //쿠키를 찾을 경로
				 cookie.setMaxAge(cookieMaxAge); //유효기간 14일
				 response.addCookie(cookie);
				 
				 Timestamp cookieEnd = new Timestamp(System.currentTimeMillis()+ (1000*cookieMaxAge));
				 
				 dao.keepLogin(user.getId(),sessionId, cookieEnd);			 
			 }
		}
		
		response.setContentType("text/html;charset=utf-8");
		request.setAttribute("result", flag);
		
		
		return "callback";
	}

}
