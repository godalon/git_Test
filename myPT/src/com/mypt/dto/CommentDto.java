package com.mypt.dto;

import java.sql.Timestamp;

public class CommentDto {
	private int  c_num, boardNum;
	private String c_nick, c_content;
	private Timestamp c_date;
	private int c_ref;
	private int c_depth;
	
	private TestPagingDto testPagingDto;
	
	
	public CommentDto() {}
	
	public CommentDto(TestPagingDto testPagingDto) 
	{
		this.testPagingDto = testPagingDto;
	}
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public String getC_nick() {
		return c_nick;
	}
	public void setC_nick(String c_nick) {
		this.c_nick = c_nick;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public Timestamp getC_date() {
		return c_date;
	}
	public void setC_date(Timestamp c_date) {
		this.c_date = c_date;
	}

	
	public int getC_ref() {
		return c_ref;
	}
	public void setC_ref(int c_ref) {
		this.c_ref = c_ref;
	}
	
	public int getC_depth() {
		return c_depth;
	}

	public void setC_depth(int c_depth) {
		this.c_depth = c_depth;
	}
	

	public TestPagingDto getTestPagingDto() {
		return testPagingDto;
	}
	public void setTestPagingDto(TestPagingDto testPagingDto) {
		this.testPagingDto = testPagingDto;
	}
	

	

}
