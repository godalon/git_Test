package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mypt.connection.DBConnection;
import com.mypt.dto.LikeDto;

public class LikeDao {

	DBConnection db;

	private static LikeDao instance = new LikeDao();

	public static LikeDao getInstance() {
		return instance;
	}

	private LikeDao() {
		db = DBConnection.getInstance();
	};

	// insert
	public int insert(LikeDto dto, String likeTblName, String boardName) {
		Connection con = null;
		PreparedStatement ps = null;

		int result = 0;

		String sql = "insert into " + likeTblName + " values(?,?)";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getNick());
			ps.setInt(2, dto.getNum());

			result = ps.executeUpdate();

			if (result == 1) {
				PboardDao.getInstance().updateLike(dto.getNum(), boardName, 1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

		return result;
	}

	// select //닉네임- 좋아요 게시글 번호
	public ArrayList<Integer> likeNum(String nickName, String likeTblName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Integer> arr = new ArrayList<Integer>();

		String sql = "select boardnum from " + likeTblName + " where l_nick=?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, nickName);

			rs = ps.executeQuery();

			while (rs.next()) {
				arr.add(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	// update 불필요

	// delete
	public int delete(LikeDto dto, String likeTblName, String boardName) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "";

		int result= 0;		
		
		sql = "delete from "+likeTblName+" where boardnum=? and l_nick=?";
		
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getNum());
			ps.setString(2, dto.getNick());

			result = ps.executeUpdate();

			if (result == 1) {
				PboardDao.getInstance().updateLike(dto.getNum(), boardName, -1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

		return result;
	}
	
	

	// 해당 유저 좋아요 여부
	public int selectLike(String nickName, String likeTblName, int num) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int flag = 0;
		String sql = "";

		sql = "select * from " + likeTblName + " where l_nick=? and boardnum=?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, nickName);
			ps.setInt(2, num);
			rs = ps.executeQuery();

			if (rs.next()) {
				flag = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return flag;
	}

	// 해당 유저 좋아요 개수
	public int userCountLike(String nickName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;
		String sql = "";

		sql = "select count(*) from cblike where l_nick=? union all " + "select count(*) from pblike where l_nick=?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, nickName);
			ps.setString(2, nickName);
			System.out.println(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				cnt += rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return cnt;
	}
}
