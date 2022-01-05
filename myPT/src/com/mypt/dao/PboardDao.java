package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.mypt.dto.PboardDto;
import com.mypt.dto.QboardDto;

public class PboardDao extends AbstractBoardDao<PboardDto> {

	private static PboardDao instance = new PboardDao();

	public static PboardDao getInstance() {
		return instance;
	}

	private PboardDao() {
	}

	public void insert(PboardDto dto) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "insert into pboard(pb_title, pb_writer, pb_content, pb_hit, pb_like, pb_photo) values(?,?,?,?,?,?)";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getWriter());
			ps.setString(3, dto.getContent());
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.setString(6, dto.getPhoto());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

	@Override
	public void update(PboardDto dto) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "update pboard set pb_title=?, pb_content=?, pb_photo=? where pb_num=?";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			ps.setString(3, dto.getPhoto());
			ps.setInt(4, dto.getNum());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

	public ArrayList<PboardDto> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<PboardDto> arr = new ArrayList<PboardDto>();

		String sql = "select * from pboard order by pb_num desc";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				PboardDto dto = new PboardDto();

				dto.setNum(rs.getInt("pb_num"));
				dto.setTitle(rs.getString("pb_title"));
				dto.setWriter(rs.getString("pb_writer"));
				String date = rs.getTimestamp("pb_date").toString();
				dto.setDate(date.substring(0, 10));
				dto.setPhoto(rs.getString("pb_photo"));
				dto.setContent(rs.getString("pb_content"));
				dto.setLike(rs.getInt("pb_like"));
				dto.setHit(rs.getInt("pb_hit"));

				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	public PboardDto detailView(int num) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		PboardDto dto = null;

		String sql = "select * from pboard where pb_num=?";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto = new PboardDto();
				dto.setNum(rs.getInt("pb_num"));
				dto.setTitle(rs.getString("pb_title"));
				dto.setWriter(rs.getString("pb_writer"));
				dto.setDate(rs.getTimestamp("pb_date").toString());
				dto.setHit(rs.getInt("pb_hit") + 1);
				dto.setContent(rs.getString("pb_content"));
				dto.setLike(rs.getInt("pb_like"));

				upCount(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return dto;

	}

///		좋아요 리스트 확인요함
	public ArrayList<PboardDto> myLikeList(ArrayList<Integer> nums) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<PboardDto> arr = new ArrayList<PboardDto>();

		String sql = "select * from pboard where num=?";

		try {
			con = db.getConnection();

			for (int num : nums) {
				ps = con.prepareStatement(sql);
				ps.setInt(1, num);

				rs = ps.executeQuery();

				if (rs.next()) {
					PboardDto dto = new PboardDto();

					dto.setTitle(rs.getString("pb_title"));
					dto.setWriter(rs.getString("pb_writer"));
					dto.setDate(rs.getTimestamp("pb_date").toString());
					dto.setPhoto(rs.getString("pb_photo"));
					dto.setLike(rs.getInt("pb_like"));
					dto.setHit(rs.getInt("pb_hit"));

					arr.add(dto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	// 댓글 수 확인
	public int commentNum(int pb_num) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		String sql = "SELECT count(*) FROM pcomment where boardnum=" + pb_num;

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return result;
	}

	// 포토 게시판 좋아요 눌렀는지 확인
	public String photoLikeCheck(int pbl_num, String nick) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = "";

		String sql = "SELECT * FROM pblike where boardnum=? and l_nick=?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, pbl_num);
			ps.setString(2, nick);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = "checked=\"checked\"";
			} else {
				result = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return result;
	}

	// 총 게시물 수
	public int getTotalCount() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = db.getConnection();
			sql = "select count(*) from pboard";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next())
				totalCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return totalCount;
	}

	// Board Total Count : 검색 시 총 게시물수
	public int getTotalCount(String keyField, String keyWord) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = db.getConnection();
			// 검색이 아닌경우
			if (keyWord.trim().equals("") || keyWord == null) {
				sql = "select count(*) from pboard";

				ps = con.prepareStatement(sql);
			} else {
				// 검색인 경우
				sql = "select count(*) from pboard where " + keyField + " like '%" + keyWord + "%'";

				System.out.println(sql);
				ps = con.prepareStatement(sql);
			}
			rs = ps.executeQuery();
			if (rs.next())
				totalCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return totalCount;
	}

	// 정해진 레코드 수 만큼 리스트 받아옴
	public ArrayList<PboardDto> getList(int startPage, int numPerPage) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<PboardDto> arr = new ArrayList<PboardDto>();

		String sql = "select * from pboard order by pb_num desc limit ?,?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, startPage);
			ps.setInt(2, numPerPage);
			rs = ps.executeQuery();

			while (rs.next()) {
				PboardDto dto = new PboardDto();

				dto.setNum(rs.getInt("pb_num"));
				dto.setTitle(rs.getString("pb_title"));
				dto.setWriter(rs.getString("pb_writer"));
				String date = rs.getTimestamp("pb_date").toString();
				dto.setDate(date.substring(0, 10));
				dto.setPhoto(rs.getString("pb_photo"));
				dto.setContent(rs.getString("pb_content"));
				dto.setLike(rs.getInt("pb_like"));
				dto.setHit(rs.getInt("pb_hit"));

				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	// 검색한 리스트 받아옴
	public ArrayList<PboardDto> getList(String keyField,String keyWord,int startPage, int numPerPage) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<PboardDto> arr = new ArrayList<PboardDto>();

		String sql = "select * from pboard where "+keyField+" like '%"+keyWord+"%' order by pb_num desc limit ?,?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, startPage);
			ps.setInt(2, numPerPage);
			rs = ps.executeQuery();

			while (rs.next()) {
				PboardDto dto = new PboardDto();

				dto.setNum(rs.getInt("pb_num"));
				dto.setTitle(rs.getString("pb_title"));
				dto.setWriter(rs.getString("pb_writer"));
				String date = rs.getTimestamp("pb_date").toString();
				dto.setDate(date.substring(0, 10));
				dto.setPhoto(rs.getString("pb_photo"));
				dto.setContent(rs.getString("pb_content"));
				dto.setLike(rs.getInt("pb_like"));
				dto.setHit(rs.getInt("pb_hit"));

				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	// 유저가 쓴글 목록
	public ArrayList<PboardDto> userList(String nick) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<PboardDto> arr = new ArrayList<PboardDto>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String today = sdf.format(cal.getTime());

		String sql = "select * from pboard where pb_writer=?";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, nick);
			rs = ps.executeQuery();

			while (rs.next()) {
				PboardDto dto = new PboardDto();

				dto.setTitle(rs.getString("pb_title"));
				dto.setWriter(rs.getString("pb_writer"));
				dto.setHit(rs.getInt("pb_hit"));
				dto.setNum(rs.getInt("pb_num"));
				
				String Date = rs.getTimestamp("pb_date").toString();
				if(Date.substring(0, 10).equals(today)) {
					dto.setDate(Date.substring(11));
				}else {
					dto.setDate(Date.substring(0,10));
				}

				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	// Count Up : 조회수 증가
	public void upCount(int num) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			con = db.getConnection();
			sql = "update pboard set pb_hit = pb_hit +1 where pb_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	// 게시물 삭제
	public void delete(int num)
	{
					
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "delete from pboard where pb_num=?";
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
}
