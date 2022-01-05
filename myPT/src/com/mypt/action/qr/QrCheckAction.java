package com.mypt.action.qr;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.HeadCountDao;

import com.mypt.dao.UserDao;
import com.mypt.dto.HeadCountDto;

import com.mypt.dto.UserDto;

public class QrCheckAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		UserDao dao = UserDao.getInstance();
		String email = request.getParameter("email");
		UserDto dto = dao.getUserByEmail(email);
		HeadCountDao hdao = HeadCountDao.getInstance();
		HeadCountDto hdto = new HeadCountDto();
		System.out.println("회원이메일"+email);
		hdto.setH_id(dto.getId());
		System.out.println(hdto.getH_id());

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String date = format.format(time);

		int result=hdao.headcountCheck(hdto.getH_id(), date);
		
		
		PrintWriter out=response.getWriter();
		
		
		if (result == 0) {
			hdao.headcountInsert(hdto);
			System.out.println("출석 성공");
			out.println("<script>alert('반갑습니다.'); </script>");
		} else if (result == 1) {
			hdao.headcountUpdate(hdto.getH_id(), date);
			System.out.println("안녕히가세요.");
			out.println("<script>alert('안녕히가세요.'); </script>");
		} else if (result == 2) {
			System.out.println("오늘 이미 다녀감");
			out.println("<script>alert('오늘 이미 다녀갔습니다.'); </script>");
		} else {
			System.out.println("출석 오류");
			out.println("<script>alert('출석 오류');</script>");
		}

		return null;
	}

}
