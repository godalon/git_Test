package com.mypt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "*.do", loadOnStartup = 1)
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 요청 명령어 command 
		String command = getCommand(request);	
		
		
		//2. 명령어에 대해 처리
		ActionFactory actionFactory = ActionFactory.getInstance();
		
		Action action = null;
		String strView = null; 
		
		
		//2-1 명령어에 해당하는 Action 타입 객체 구하기
		action = actionFactory.getAction(command);
		
		if(action==null)
		{
			System.out.println(command+"를 처리하는 Action이 없습니다.");
			return;
		}
		
		//2-2 Action 객체가 있으면 execute() 호출.
		try 
		{
			strView= action.execute(request, response);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}			
			
		//3. 화면응답 또는 리다이렉트 이동
		if (strView == null) 
		{
			return;
		}
		
//		특정 문자열로 시작하면 true
		if (strView.startsWith("redirect:")) 
		{	
			String redirectPath = strView.substring("redirect:".length());
			response.sendRedirect(redirectPath);
		}else if(strView=="callback") {
		      PrintWriter out = response.getWriter();      
		      out.println(request.getAttribute("result"));
		      System.out.println(request.getAttribute("result"));
		      
		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			String jspPath = prefix + strView + suffix;
			RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
			dispatcher.forward(request, response);
		}
		
	} // doGet()

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");  //필터로 처리
		//response.setCharacterEncoding("utf-8"); 
		doGet(request, response);
	}
	
	
	private String getCommand(HttpServletRequest request) {
//		URI가져오기 

		String requestURI = request.getRequestURI();
		System.out.println("URI 주소: " + requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath); 

		int beginIndex = contextPath.length();
		String command = requestURI.substring(beginIndex);
		System.out.println("command: " + command); 
		
//		.do빼기
		int endIndex = command.indexOf(".do");
		command = command.substring(0, endIndex);
		System.out.println("command: " + command); 
		
		return command;
	}

}



