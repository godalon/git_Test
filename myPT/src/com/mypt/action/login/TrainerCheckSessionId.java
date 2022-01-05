package com.mypt.action.login;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mypt.controller.Action;
import com.mypt.dao.AdminDao;
import com.mypt.dao.TrainerDao;
import com.mypt.dto.AdminDto;
import com.mypt.dto.TrainerDto;

public class TrainerCheckSessionId implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		TrainerDao tdao = TrainerDao.getInstance();
		AdminDao adao = AdminDao.getInstance();
			
		HttpSession session= request.getSession();

		if(session.getAttribute("id")==null) //로그인 되어 있지 않은 경우
		{
		 Cookie cookies[] = request.getCookies();
		 Cookie cookie=null;
		 TrainerDto trainer= null;
		 AdminDto admin= null;
		 
		    if(cookies!=null)
		    {		         
		        for(Cookie c: cookies)
		        {
		        	if(c.getName().equals("myPTLoginCookie")) //로그인 용 쿠키 찾으면
		        	{
		        		cookie= c;
		        		String sessionId=c.getValue();		        		
	        			admin= adao.checkSessionId(sessionId); 
	        			trainer= tdao.checkSessionId(sessionId);
		        				      	        		
		        		break;
		        	}
		        }
		    }
		 	    
		    if(admin!=null)
		    {
				 session.setAttribute("nick", "관리자");
				 session.setAttribute("grade", 0);
				 
				 request.setAttribute("result", 2);
		    }
		    else if(trainer!=null)
		    {
		    	 session.setAttribute("name", trainer.getT_name());
				 session.setAttribute("nick", trainer.getT_nick());
				 session.setAttribute("id", trainer.getT_id());
				 session.setAttribute("grade", 1);
       		 
				 request.setAttribute("result", 1);
       		 
		    }	   
		} 
		
		else if(session.getAttribute("id")!=null) //세션에 아이디 값이 남아있음
		{
			if(session.getAttribute("grade").equals(0))
			{
				 request.setAttribute("result", 2);
			}
			else
			{
				request.setAttribute("result", 1);
			}
			
		}
			
		
		else
		{
			request.setAttribute("result", 0);	
		}
		
		
		return "callback";
	}

}
