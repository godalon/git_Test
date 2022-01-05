package com.mypt.dto;

import com.google.gson.JsonArray;

public class QboardDto extends BoardDto 
{
	private int ref;
	private int depth;
	private int pos;
	
	private JsonArray comments;
	
	public QboardDto(){}
	
	public QboardDto(JsonArray comments)
	{
		this.comments = comments;
	}
	
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public JsonArray getComments() {
		return comments;
	}
	public void setComments(JsonArray comments) {
		this.comments = comments;
	}
	
	
	
	
}
