package com.mypt.dto;

import java.sql.Timestamp;

public class BoardDto 
{
	private int num;
	private String title;
	private String writer;
	private String head;
	private String content;
	private int hit;
	private String date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title == null ? "" : title.trim();
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer == null ? "" : writer.trim();
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getHead() {
		return head == null ? "" : head.trim();
	}
	public void setHead(String head) {
		this.head = head;
	}

	public String getContent() {
		return content == null ? "" : content.trim();
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
