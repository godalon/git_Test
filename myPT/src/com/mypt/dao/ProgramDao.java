package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mypt.connection.DBConnection;
import com.mypt.dto.ProgramDto;

public class ProgramDao {
	private DBConnection db;

//////////dao 싱글톤 (이)
	private static ProgramDao instance = new ProgramDao();

	public static ProgramDao getInstance() {
		return instance;
	}

	private ProgramDao() {
		db = DBConnection.getInstance();
	}

	public void insertProgram(ProgramDto programBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "insert program(p_id,p_date,p_mention,p_part) " + "values(?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, programBean.getP_id());
			ps.setString(2, programBean.getP_date());
			ps.setString(3, programBean.getP_mention());
			ps.setString(4, programBean.getP_part());

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

	public ProgramDto getProgram(String p_id,String p_date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		ProgramDto programBean = new ProgramDto();

		try {
			con = db.getConnection();
			sql = "select * from program where p_id=? and p_date=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, p_id);
			ps.setString(2, p_date);
			rs = ps.executeQuery();
			if (rs.next()) {
				programBean.setP_id(rs.getString("p_id"));
				programBean.setP_date(rs.getString("p_date"));
				programBean.setP_mention(rs.getString("p_mention"));
				programBean.setP_part(rs.getString("p_part"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return programBean;
	}

	public void updateProgram(ProgramDto programBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "update program set p_mention=?,p_part=? where p_id=? and p_date=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, programBean.getP_mention());
			ps.setString(2, programBean.getP_part());
			ps.setString(3, programBean.getP_id());
			ps.setString(4, programBean.getP_date());

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

	}

	public void deleteProgram(String p_id, String p_date) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "delete from program where p_id=? and p_date=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, p_id);
			ps.setString(2, p_date);

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	public int isProgramExist(String p_id, String p_date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int flag=0;

		try {
			con = db.getConnection();
			sql = "select * from program where p_id=? and p_date=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, p_id);
			ps.setString(2, p_date);
			rs = ps.executeQuery();
			if (rs.next()) {
				flag=1;
			}else {
				flag=0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return flag;
	}
	
}
