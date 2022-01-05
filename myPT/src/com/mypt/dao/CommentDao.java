package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mypt.connection.DBConnection;
import com.mypt.dto.CommentDto;

public class CommentDao {
	private DBConnection db;

	private static CommentDao instance = new CommentDao();

	public static CommentDao getInstance() {
		return instance;
	}

	private CommentDao() {
		db = DBConnection.getInstance();
	}

	public int commentInsert(String commenttblName, CommentDto comment) {

		Connection con = null;
		PreparedStatement ps = null;

		int result = 0;

		try {
			con = db.getConnection();

//			String sql = "insert into "+ commenttblName+"(boardNum, c_nick, c_content) values(?,?,?)";
//			ps = con.prepareStatement(sql);
//			ps.setInt(1, comment.getBoardNum());			
//			ps.setString(2, comment.getC_nick());			
//			ps.setString(3, comment.getC_content());	
			
			String sql = "insert into " + commenttblName + "(boardNum, c_nick, c_content) values(?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, comment.getBoardNum());
			ps.setString(2, comment.getC_nick());
			ps.setString(3, comment.getC_content());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

		return result;

	}

	// 최근에 입력된 댓글
	public CommentDto getLastComment(String commenttblName, int boardNum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CommentDto cd = new CommentDto();

		try {
			con = db.getConnection();
			String sql = "SELECT * FROM " + commenttblName + " WHERE boardnum=? ORDER BY c_num DESC LIMIT 1";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();

			if (rs.next()) {
				cd.setC_num(rs.getInt(1));
				cd.setBoardNum(boardNum);
				cd.setC_nick(rs.getString(3));
				cd.setC_content(rs.getString(4));
				cd.setC_date(rs.getTimestamp(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return cd;
	}

	// detailview에서의 댓글리스트
	public ArrayList<CommentDto> commentList(String commenttblName, int boardNum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<CommentDto> arr = new ArrayList<CommentDto>();
		CommentDto cd = null;

		try {
			con = db.getConnection();
			String sql = "select * from " + commenttblName + " where boardNum=?";
			ps = con.prepareStatement(sql);

			ps.setInt(1, boardNum);
			rs = ps.executeQuery();

			while (rs.next()) {
				cd = new CommentDto();
				cd.setC_num(rs.getInt(1));
				cd.setBoardNum(boardNum);
				cd.setC_nick(rs.getString(3));
				cd.setC_content(rs.getString(4));
				cd.setC_date(rs.getTimestamp(5));

				arr.add(cd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	public int commentDelete(String commenttblName, int cnum) {
		Connection con = null;
		PreparedStatement ps = null;

		int result = 0;

		try {
			con = db.getConnection();
			String sql = "delete from " + commenttblName + " where c_num=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, cnum);

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

		return result;

	}

	public int commentUpdate(String commenttblName, CommentDto cd) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			con = db.getConnection();
			String sql = "update " + commenttblName + " set c_content=? where c_num=?";

			ps = con.prepareStatement(sql);
			ps.setString(1, cd.getC_content());
			ps.setInt(2, cd.getC_num());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

		return result;
	}

//start : 시작번호, cnt : 한 페이지당 가져올 게시물 개수
//	한 페이지에 해당하는 댓글 가져오기  
//	public ArrayList<CommentDto> getCommentsForOneCommentPage(String commenttblName, int boardNum, int start, int cnt)
	public JsonArray getCommentsForOneCommentPage(String commenttblName, int boardNum, int start, int cnt) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

//		ArrayList<CommentDto> comments= new ArrayList<>();
		Gson gson = new Gson();
		JsonArray comments = new JsonArray();

		CommentDto cd = null;
		String sql = "SELECT * FROM " + commenttblName + " WHERE boardNum=? ORDER BY c_num LIMIT ?,?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setInt(1, boardNum);
			ps.setInt(2, start - 1);
			ps.setInt(3, cnt);

			rs = ps.executeQuery();

			while (rs.next()) {
				cd = new CommentDto();
				cd.setC_num(rs.getInt(1));
				cd.setBoardNum(boardNum);
				cd.setC_nick(rs.getString(3));
				cd.setC_content(rs.getString(4));
				cd.setC_date(rs.getTimestamp(5));
//					cd.setC_ref(rs.getInt(6));

//					comments.add(cd);	

				comments.add(gson.fromJson(gson.toJson(cd), JsonObject.class));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return comments;
	}

	// 해당 글 댓글 수 가져오기 // String sql = "select count(c_num) from "+ commenttblName+ "
	// where boardnum=?"
	public int countComment(String commenttblName, int boardNum) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;

		try {
			con = db.getConnection();
			String sql = "SELECT count(*) FROM " + commenttblName + " WHERE boardnum=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();
			System.out.println(sql);

			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return cnt;
	}

	// 유저 댓글 수
	public int countUserComment(String nick) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = 0;

		try {
			con = db.getConnection();
			String sql = "select count(*) from ccomment where c_nick=? union all "
					+ "select count(*) from qcomment where c_nick=? union all " + "select count(*) from pcomment where c_nick=?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, nick);
			ps.setString(2, nick);
			ps.setString(3, nick);
			rs = ps.executeQuery();
			System.out.println(sql);

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

	// 유저 댓글리스트
	public ArrayList<CommentDto> usercommentList(String commenttblName, String nick) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CommentDto> arr = new ArrayList<CommentDto>();
		CommentDto cd = null;

		try {
			con = db.getConnection();
			String sql = "select * from " + commenttblName + " where c_nick=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, nick);
			rs = ps.executeQuery();

			while (rs.next()) {
				cd = new CommentDto();
				cd.setC_num(rs.getInt("c_num"));
				cd.setBoardNum(rs.getInt("boardnum"));
				cd.setC_nick(rs.getString("c_nick"));
				cd.setC_content(rs.getString("c_content"));
				cd.setC_date(rs.getTimestamp("c_date"));
				arr.add(cd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

}
