package com.mypt.dao;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String t_Id = "T0001";
		String t_photo = "황철순.jpg";
		
		String newFileName= t_Id+"."+t_photo.substring(t_photo.lastIndexOf(".")+1);
		
		System.out.println(newFileName);

	}

}
