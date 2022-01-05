package com.mypt.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.mypt.controller.Action;
import com.mypt.dao.CboardDao;
import com.mypt.dao.LikeDao;
import com.mypt.dao.PboardDao;
import com.mypt.dao.QboardDao;
import com.mypt.dto.CboardDto;
import com.mypt.dto.PboardDto;
import com.mypt.dto.QboardDto;

public class UpdateViewAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int num = Integer.parseInt(request.getParameter("num"));
		String board = session.getAttribute("board").toString();

		if (board.equals("cboard")) {
			CboardDto dto = new CboardDto();
			CboardDao dao = CboardDao.getInstance();
			LikeDao ldao = LikeDao.getInstance();
			dto = dao.detailView(num);

			JSONObject obj = new JSONObject();
			obj.put("content", dto.getContent());

			request.setAttribute("result", obj);
		}else if(board.equals("qboard")) {
			QboardDto dto = new QboardDto();
			QboardDao dao = QboardDao.getInstance();
			LikeDao ldao = LikeDao.getInstance();
			dto = dao.detailView(num);

			JSONObject obj = new JSONObject();
			obj.put("content", dto.getContent());

			request.setAttribute("result", obj);
		}else if(board.equals("pboard")) {
			PboardDto dto = new PboardDto();
			PboardDao dao = PboardDao.getInstance();
			LikeDao ldao = LikeDao.getInstance();
			dto = dao.detailView(num);
			String content=dto.getContent();
			System.out.println(content);
			String target="<img";
			String targetEnd=">";
			System.out.println(content.indexOf("<img"));
			if(content.contains(target)) {
				int targetNum=content.indexOf(target);
				System.out.println(targetNum);
				String photo=content.substring(targetNum, content.substring(targetNum).indexOf(targetEnd)+targetNum)+"class=\"card-img-top mb-1\" />";
				dto.setPhoto(photo);
			}

			JSONObject obj = new JSONObject();
			obj.put("content", dto.getContent());

			request.setAttribute("result", obj);
		}

		return "callback";
	}

}
