package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mypt.connection.DBConnection;
import com.mypt.dto.HeadCountDto;

public class HeadCountDao {
	private DBConnection db;
	
//////dao 싱글톤 (이)
	private static HeadCountDao instance = new HeadCountDao();
	public static HeadCountDao getInstance() 
	{
		return instance;
	}
	
	private HeadCountDao() {
		db = DBConnection.getInstance();
	}
	
	public void headcountInsert(HeadCountDto hd) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = db.getConnection();
			String sql = "insert into headcount(h_id) values(?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, hd.getH_id());
			ps.executeUpdate();			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	
	public int headcountSelect(String h_id, String intime) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = db.getConnection();
			String sql = "select count(*) from headcount where h_id=? and intime=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, h_id);
			ps.setString(2, intime);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = Integer.parseInt(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return count;
		
	}
	
	public void headcountUpdate(String h_id, String date) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = db.getConnection();
			String sql = "update headcount set outtime=now() where h_id=? and intime like concat('%',?,'%')";
			ps = con.prepareStatement(sql);			
			ps.setString(1, h_id);
			ps.setString(2,date);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}				
	}
	
	public void headcountDelete(String h_id, String date) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = db.getConnection();
			String sql = "delete from headcount where h_id=? and intime=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, h_id);
			ps.setString(2, date);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	public int headcountCheck(String h_id,String date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		
		int result=0;
		
		try {
			con = db.getConnection();
			String sql = "select * from headcount where h_id=? and intime like concat('%',?,'%')";
			ps = con.prepareStatement(sql);
			ps.setString(1, h_id);
			ps.setString(2, date);
			rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("outtime")==null) {
					result=1;
				}else {
					result=2;
				}
			}else {
				result=0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		
		return result;
	}
	
	
	public int crowdedCheck(String date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		
		int result=0;
		
		try {
			con = db.getConnection();
			String sql = "select count(*) from headcount where intime like '%"+date+"%' and outtime is null;";
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		
		return result;
	}
	
	public ArrayList<HeadCountDto> headcountList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<HeadCountDto> arr = new ArrayList<HeadCountDto>();	
		
		try {
			con = db.getConnection();
			String sql = "select u.name,h.* from headcount h "
					+ "left outer join user u on h.h_id = u.id";
			ps = con.prepareStatement(sql);			
			rs = ps.executeQuery();
			while(rs.next()) {
				HeadCountDto dto = new HeadCountDto();
				dto.setU_name(rs.getString("name"));
				dto.setH_id(rs.getString("h_id"));
				dto.setIntime(rs.getTimestamp("intime"));
				dto.setOuttime(rs.getTimestamp("outtime"));
				
				arr.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return arr;
		
	}
	
	

}
