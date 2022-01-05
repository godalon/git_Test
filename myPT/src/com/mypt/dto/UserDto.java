package com.mypt.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class UserDto {
	private InbodyDto inbody;
	private HistoryDto history;
	
	private String id;
	private String pw;
	private String name;
	private String gender;
	private String email;
	private String birth;
	private String address;
	private String qr;
	private Timestamp signdate;
	private String nick;

	//date->String
	private String startdate;
	private String enddate;	

	private int ptcount;
	private String tid;
	private String zipcode;
	private String tel;
	private String addrdetail;
		
	private String sessionId;
	private Timestamp cookieEnd;
	
	
	public UserDto() {};
	public UserDto(InbodyDto inbody)
	{
		this.inbody= inbody; 
	}
	
	public UserDto(HistoryDto history)
	{
		this.history= history; 
	}
	
	public InbodyDto getInbody() {
		return inbody;
	}
	public void setInbody(InbodyDto inbody) {
		this.inbody = inbody;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
	}
	public Timestamp getSigndate() {
		return signdate;
	}
	public void setSigndate(Timestamp signdate) {
		this.signdate = signdate;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	
//	public Date getStartdate() {
//		return startdate;
//	}
//	public void setStartdate(Date startdate) {
//		this.startdate = startdate;
//	}
//	public Date getEnddate() {
//		return enddate;
//	}
//	public void setEnddate(java.util.Date date) {
//		this.enddate = (Date) date;
//	}
	
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
	
	public String getTid() {
		return tid;
	}
	
	public void setTid(String tid) {
		this.tid = tid;
	}
	public int getPtcount() {
		return ptcount;
	}
	public void setPtcount(int ptcount) {
		this.ptcount = ptcount;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddrdetail() {
		return addrdetail;
	}
	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}
	
	
	public HistoryDto getHistory() {
		return history;
	}
	public void setHistory(HistoryDto history) {
		this.history = history;
	}
	
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Timestamp getCookieEnd() {
		return cookieEnd;
	}
	public void setCookieEnd(Timestamp cookieEnd) {
		this.cookieEnd = cookieEnd;
	}
	
	

	
	
	
	
	
	 
}
