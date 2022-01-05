package com.mypt.dto;

import java.util.ArrayList;

import com.google.gson.JsonArray;

public class PboardDto extends BoardDto
{
	private String photo;
	private int like;
	
	private JsonArray comments;
	
	public PboardDto(){}
	
	public PboardDto(JsonArray comments)
	{
		this.comments = comments;
	}
	
	
	public String getPhoto() {
		return photo == null ? "" : photo.trim();
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}

	public JsonArray getComments() {
		return comments;
	}

	public void setComments(JsonArray comments) {
		this.comments = comments;
	}

	
}
