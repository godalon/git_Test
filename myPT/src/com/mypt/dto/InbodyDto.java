package com.mypt.dto;

import java.sql.Timestamp;

public class InbodyDto {
	private String i_id;
	private Timestamp i_date;
	private String strDate;
	private float muscle;
	private float fat;
	private float height;
	private float weight;
	
	public String getI_id() {
		return i_id;
	}
	public void setI_id(String i_id) {
		this.i_id = i_id;
	}
	public Timestamp getI_date() {
		return i_date;
	}
	public void setI_date(Timestamp i_date) {
		this.i_date = i_date;
	}
	public float getMuscle() {
		return muscle;
	}
	public void setMuscle(float muscle) {
		this.muscle = muscle;
	}
	public float getFat() {
		return fat;
	}
	public void setFat(float fat) {
		this.fat = fat;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	
	public String getStrDate() 
	{
		return strDate;
	}
	public void setStrDate(String strDate) 
	{
		this.strDate = strDate;
	}
	
	
	
	
}
