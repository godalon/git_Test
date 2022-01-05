package com.mypt.action.move;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mypt.controller.Action;
import com.mypt.dao.InbodyDao;
import com.mypt.dto.UserDto;

public class MoveInbody implements Action
{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		return "user/inbody";
	}

}
