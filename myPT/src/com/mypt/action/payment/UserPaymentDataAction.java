package com.mypt.action.payment;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mypt.controller.Action;
import com.mypt.dao.HistoryDao;
import com.mypt.dao.UserDao;
import com.mypt.dto.HistoryDto;
import com.mypt.dto.UserDto;

public class UserPaymentDataAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
			
		UserDao userDao = UserDao.getInstance();
		
		Gson gson = new Gson();	
		
		String jsonStr = readJSONFromRequestBody(request);
		
		HistoryDao dao= HistoryDao.getInstance();		
		UserDto payUser= gson.fromJson(jsonStr, UserDto.class);

		HistoryDto history= payUser.getHistory();

		//유저 정보 확인
		UserDto beforeUser = userDao.getUserById(history.getHid());
		beforeUser.setHistory(history);
		
		//결제내역 history테이블에 입력
		dao.insertHistory(history);
		
		payUser.setId(beforeUser.getId());
		payUser.setTid(history.getTid());
		
		
		
        //결제한 상품(pt횟수)		
		int count= history.getHcount();
		
		//결제한 일수
		int days =0;
		
		switch(count)
		{				
			case 10:
				days= 30;
				break;
				
			case 23:
				days= 60;
				break;
				
			case 35:
				days =90;
				break;
				
			case 60:
				days= 180;
				break;
		}
		
		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd");	
		Calendar cal = sf.getCalendar();
		System.out.println("널확인"+sf.parse(payUser.getStartdate()));
		System.out.println("널확인"+payUser.getStartdate());
		
		//enddate가 지나지 않은. (ptcount 0 or not 0)
		if(beforeUser.getEnddate()!=null)
		{
			//enddate+enddate, +ptcount
			cal.setTime(sf.parse(beforeUser.getEnddate()));
			cal.add(Calendar.DATE, days);
			beforeUser.setEnddate(sf.format(cal.getTime()));
				
			userDao.updateAfterPayment(beforeUser);
		}	
		//enddate가 없는 사람 //startdate 기준으로 enddate계산
		else
		{
//			System.out.println("널확인"+sf.parse(payUser.getStartdate()));
//			System.out.println("널확인"+payUser.getStartdate());
			cal.setTime(sf.parse(payUser.getStartdate()));
			
			cal.add(Calendar.DATE, days);
			payUser.setEnddate(sf.format(cal.getTime()));		
			userDao.updateAfterPayment(payUser);
		}
		
		
		return "callback";
	}
	
	
//	request바디에서 문자열 읽기
	private String readJSONFromRequestBody(HttpServletRequest request)
	{
	    StringBuffer sb = new StringBuffer();
	    String line = null;
	 
	    try {
	        BufferedReader reader = request.getReader();
	        while((line = reader.readLine()) != null) 
	        {
	            sb.append(line);
	        }
	 
	    }catch(Exception e) 
	    {
	        System.out.println(e.toString());
	    }
	    return sb.toString();
	}
	


}
