package com.mypt.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
	
	private static DBConnection instance = new DBConnection();
	public static DBConnection getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception { // 자바sql 임포트
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");	//lookup으로 파일 찾음
		DataSource ds = (DataSource)envCtx.lookup("jdbc/mysql");
		return ds.getConnection();
	}
	
	public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con ) {
		// TODO Auto-generated method stub
		try {
			
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
