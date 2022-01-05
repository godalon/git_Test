package com.mypt.action.login;


import java.sql.Timestamp;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.UserDao;
import com.mypt.dto.UserDto;
import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;

public class UserCheckSessionId implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		UserDao dao = UserDao.getInstance();
		
		HttpSession session= request.getSession();

		if(session.getAttribute("id")==null) //로그인 되어 있지 않은 경우
		{
		 Cookie cookies[] = request.getCookies();
		 Cookie cookie=null;
		 UserDto user= null;
		 
		    if(cookies!=null)
		    {		         
		        for(Cookie c: cookies)
		        {
		        	if(c.getName().equals("myPTLoginCookie")) //로그인 용 쿠키 찾으면
		        	{
		        		cookie= c;
		        		String sessionId=c.getValue();
		        		user= dao.checkSessionId(sessionId); //유저정보 찾아옴		      	        		
		        		break;
		        	}
		        }
		    }
		 
		    if(user!=null)
		    {
		     session.setAttribute("id", user.getId()); 
       		 session.setAttribute("name", user.getName());
       		 session.setAttribute("nick", user.getNick());		
       		 session.setAttribute("email", user.getEmail());
       		 session.setAttribute("grade", 2);
       		 
       		 request.setAttribute("result", 1);
       		 
		    }
		} 
		
		else if(session.getAttribute("id")!=null) //세션에 아이디 값이 남아있음
		{
			request.setAttribute("result", 1);
		}
			
		else
		{
			request.setAttribute("result", 0);	
		}
		
		
		return "callback";
	}

}
