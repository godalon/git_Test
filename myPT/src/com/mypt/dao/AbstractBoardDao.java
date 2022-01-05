package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.mypt.connection.DBConnection;
import com.mypt.dto.BoardDto;


public abstract class AbstractBoardDao<T extends BoardDto> 
{
	protected DBConnection db;
	
	public AbstractBoardDao() 
	{
		db= DBConnection.getInstance();
	}
	
	abstract public void insert(T dto);
	
	abstract public void update(T dto);
	
	abstract public ArrayList<T> getList();
		
	abstract public T detailView(int num);
	
	
	public void delete(int num, String boardName)
	{
					
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "delete from "+boardName+ " where num=?";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, num);

			ps.executeUpdate();

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeConnection(null, ps, con);
		}
	}
	
	public void updateHit(int num, String boardName)
	{
		Connection con = null;
		PreparedStatement ps = null;

		int hit=0;

		String sql = "update "+ boardName+" set hit=hit+1 where num=?";
		try 
		{
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, num);
			
			ps.executeUpdate();

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			db.closeConnection(null, ps, con);
		}

	}
	
//	numForCal 1 or -1
	public void updateLike(int num, String boardName, int numForCal)
	{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";

		if(boardName.equals("cboard")){
			sql = "update "+boardName+" set cb_like=cb_like+? where cb_num=?";
		}else{
			sql = "update "+boardName+" set pb_like=pb_like+? where pb_num=?";
		}
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, numForCal);
			ps.setInt(2, num);

			ps.executeUpdate();

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			db.closeConnection(null, ps, con);
		}
	}

	
}
