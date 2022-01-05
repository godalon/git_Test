package com.mypt.dto;

public class LikeDto 
{
	
	private String nick;
	private int num;
		
	public String getNick() {
		return nick == null ? "" : nick.trim();
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
