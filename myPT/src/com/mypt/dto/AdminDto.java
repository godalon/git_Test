package com.mypt.dto;

import java.sql.Timestamp;

public class AdminDto {
	private String id;
	private String pw;
	private String sessionId;
	private Timestamp cookieEnd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
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
