package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.mypt.connection.DBConnection;
import com.mypt.dto.AdminDto;
import com.mypt.dto.UserDto;

public class AdminDao {
	private DBConnection db;
	private static AdminDao instance = new AdminDao();
	public static AdminDao getInstance() {
		return instance;
	}

	private AdminDao() {
		db = DBConnection.getInstance();
	}

	public void insertAdmin(AdminDto adminBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "insert admin(id,pw) values(?,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, adminBean.getId());
			ps.setString(2, adminBean.getPw());

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

	public AdminDto getAdmin(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		AdminDto adminBean = new AdminDto();

		try {
			con = db.getConnection();
			sql = "select * from admin where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				adminBean.setId(rs.getString("id"));
				adminBean.setPw(rs.getString("pw"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return adminBean;
	}

	public void updateAdmin(AdminDto adminBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "update admin set pwd=? where id=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, adminBean.getPw());
			ps.setString(2, adminBean.getId());

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

	}

	public void deleteAdmin(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "delete from admin where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	//관리자 로그인 (윤)
		public int adminLogin(String id, String pw) {
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			String sql=null;
			int flag = 0;			
			
			try {
				con=db.getConnection();
				sql="select * from admin where id=? and pw=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, pw);
				rs=ps.executeQuery();
				if(rs.next()) {
					flag = 2;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return flag;
		}
		
		

		//세션id, 유효시간 저장(쿠키)
			public int keepLogin(String sessionId, Timestamp cookieEnd)
			{
				Connection con = null;
				PreparedStatement ps = null;
				
				int result=0;

				String sql = "update admin set sessionId=?, cookieEnd=? where id='admin'";
				try {
					con = db.getConnection();
					ps = con.prepareStatement(sql);

					ps.setString(1, sessionId);
					ps.setTimestamp(2, cookieEnd);

					result= ps.executeUpdate();
					System.out.println("관리자 쿠키 result:"+ result);

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				} 
				finally 
				{
					db.closeConnection(null, ps, con);
				}
				
				return result;
				
			}
			
			public AdminDto checkSessionId(String sessionId)  //세션아이디로 값 가져오기
			{
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;

				AdminDto dto= null;

				String sql = "select * from admin where sessionId=? and cookieEnd>now()";

				try {
					con = db.getConnection();
					ps = con.prepareStatement(sql);
					
					ps.setString(1, sessionId);
					
					rs = ps.executeQuery();

					if(rs.next()) 
					{
						dto = new AdminDto();
						dto.setId(rs.getString(1));
						
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				} 
				finally 
				{
					db.closeConnection(rs, ps, con);
				}
				
				return dto;

			}
		

}
