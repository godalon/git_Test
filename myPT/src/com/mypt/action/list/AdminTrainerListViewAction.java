package com.mypt.action.list;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dto.TrainerDto;

public class AdminTrainerListViewAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TrainerDao dao= TrainerDao.getInstance();
		
		ArrayList<TrainerDto> arr=dao.trainerList();
		request.setAttribute("trainerList", arr);
		return "admin/trainerList";
	}

}
