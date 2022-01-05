package com.mypt.dto;

import java.sql.Timestamp;

public class TrainerDto {
	
	private String t_id, t_pw, t_name, t_email, t_birth, t_address, t_nick,t_gender,t_tel,t_zipcode,t_addrdetail, t_photo;
	private String sessionId;
	private Timestamp cookieEnd;

	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getT_pw() {
		return t_pw;
	}
	public void setT_pw(String t_pw) {
		this.t_pw = t_pw;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_email() {
		return t_email;
	}
	public void setT_email(String t_email) {
		this.t_email = t_email;
	}
	public String getT_birth() {
		return t_birth;
	}
	public void setT_birth(String t_birth) {
		this.t_birth = t_birth;
	}
	public String getT_address() {
		return t_address;
	}
	public void setT_address(String t_address) {
		this.t_address = t_address;
	}
	public String getT_nick() {
		return t_nick;
	}
	public void setT_nick(String t_nick) {
		this.t_nick = t_nick;
	}
	public String getT_gender() {
		return t_gender;
	}
	public void setT_gender(String t_gender) {
		this.t_gender = t_gender;
	}
	public String getT_tel() {
		return t_tel;
	}
	public void setT_tel(String t_tel) {
		this.t_tel = t_tel;
	}
	public String getT_zipcode() {
		return t_zipcode;
	}
	public void setT_zipcode(String t_zipcode) {
		this.t_zipcode = t_zipcode;
	}
	public String getT_addrdetail() {
		return t_addrdetail;
	}
	public void setT_addrdetail(String t_addrdetail) {
		this.t_addrdetail = t_addrdetail;
	}
	
	public String getT_photo() {
		return t_photo;
	}
	public void setT_photo(String t_photo) {
		this.t_photo = t_photo;
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
