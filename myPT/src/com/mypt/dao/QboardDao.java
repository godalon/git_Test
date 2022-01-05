package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import com.mypt.dto.CboardDto;
import com.mypt.dto.QboardDto;



public class QboardDao extends AbstractBoardDao<QboardDto>
{

	private static QboardDao instance = new QboardDao();
	public static QboardDao getInstance() 
	{
		return instance;
	}
	
	private QboardDao(){}
	
	@Override
	public void insert(QboardDto dto) 
	{
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "insert into qboard(qb_title, qb_writer, qb_content, hit, qb_ref, qb_depth, qb_pos) values(?,?,?,?,?,?,?,?)";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getWriter());			
			ps.setString(4, dto.getContent());
			ps.setInt(5, 0);
			ps.setInt(6, dto.getRef());
			ps.setInt(7, dto.getDepth());
			ps.setInt(8, dto.getPos());
			
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

	@Override
	public void update(QboardDto dto) 
	{
		Connection con = null;
		PreparedStatement ps = null;

		String sql = "update qboard set qb_title=?, qb_content=? where qb_num=?";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getContent());
			
			ps.setInt(3, dto.getNum());

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

	@Override
	public ArrayList<QboardDto> getList() 
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<QboardDto> arr = new ArrayList<QboardDto>();

		String sql = "select qb_title, qb_writer, qb_date, hit from qboard";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				QboardDto dto = new QboardDto();

				dto.setTitle(rs.getString("qb_title"));
				dto.setWriter(rs.getString("qb_writer"));
				dto.setDate(rs.getTimestamp("qb_date").toString());
				dto.setHit(rs.getInt("hit"));

				arr.add(dto);
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

		return arr;
	}

	@Override
	public QboardDto detailView(int num) 
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		QboardDto dto = null;

		String sql = "select * from qboard where qb_num=?"; 

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);			
			ps.setInt(1, num);
			
			rs = ps.executeQuery();

			if (rs.next()) 
			{
				dto = new QboardDto();

				dto.setNum(num);
				dto.setTitle(rs.getString("qb_title"));
				dto.setWriter(rs.getString("qb_writer"));
				dto.setDate(rs.getTimestamp("qb_date").toString());
				dto.setHit(rs.getInt("qb_hit")+1);
				dto.setContent(rs.getString("qb_content"));			
				dto.setRef(rs.getInt("qb_ref"));	
				dto.setDepth(rs.getInt("qb_depth"));	
				dto.setPos(rs.getInt("qb_pos"));	
				
				upCount(num); 
				
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
	
	//Board Insert : 파일업로드, contentType, ref의 상대적인 위치값
			public void insertBoard(QboardDto dto) {
				Connection con = null;
				PreparedStatement ps = null;
				String sql = null;
				try {
					
					///답변을 위한 ref 설정/////
					int ref = getMaxNum() + 1;
					con = db.getConnection();
					sql = "insert into qboard(qb_writer,qb_content,qb_title,qb_ref,qb_pos,qb_depth,";
					sql += "qb_hit)";
					sql += "values(?, ?, ?, ?, 0, 0, 0)";
					ps = con.prepareStatement(sql);
					ps.setString(1, dto.getWriter());
					ps.setString(2, dto.getContent());
					ps.setString(3, dto.getTitle());
					ps.setInt(4, ref);
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(null, ps, con);
				}
			}
			
		//Board Max Num : qb_ref에 저장에 필요한 기능
			public int getMaxNum() {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = null;
				int maxNum = 0;
				try{
					con = db.getConnection();
					sql = "select max(qb_num) from qboard";
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					if(rs.next()) maxNum = rs.getInt(1);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(rs,ps,con);
				}
				return maxNum;
			}
			
		//Board Total Count : 총 게시물수
			public int getTotalCount() {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = null;
				int totalCount = 0;
				try {
					con = db.getConnection();
					//검색이 아닌경우
											
					sql = "select count(*) from qboard";										
					ps = con.prepareStatement(sql);
					
					rs = ps.executeQuery();
					if(rs.next()) totalCount = rs.getInt(1);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(rs,ps,con);
				}
				return totalCount;
			}
			
	
	//검색
//			public int getTotalCount(String keyField, String keyWord) {
//				Connection con = null;
//				PreparedStatement ps = null;
//				ResultSet rs = null;
//				String sql = null;
//				int totalCount = 0;
//				try {
//					con = db.getConnection();
//					//검색이 아닌경우
//					if(keyWord.trim().equals("")||keyWord==null) {						
//							sql = "select count(*) from qboard";										
//						ps = con.prepareStatement(sql);
//					}else {
//						//검색인 경우
//						
//						sql = "select count(*) from qboard where " 
//								+ keyField +" like '%"+keyWord+"%'";	
//						System.out.println(sql);
//						ps = con.prepareStatement(sql);
//						
//					}
//					rs = ps.executeQuery();
//					if(rs.next()) totalCount = rs.getInt(1);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} finally {
//					db.closeConnection(rs,ps,con);
//				}
//				return totalCount;
//			}
			
			//검색
			public int getTotalCount(int ref) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = null;
				int totalCount = 0;
				try {
					con = db.getConnection();
					
						sql = "select count(*) from qboard where qb_ref=?";	
						System.out.println(sql);
						ps = con.prepareStatement(sql);
						ps.setInt(1, ref);
						
					
					rs = ps.executeQuery();
					if(rs.next()) totalCount = rs.getInt(1);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(rs,ps,con);
				}
				return totalCount;
			}
			
			
			
			//Count Up : 조회수 증가
			public void upCount(int num) {
				Connection con = null;
				PreparedStatement ps = null;
				String sql = null;
				try {
					con = db.getConnection();
					sql = "update qboard set qb_hit = qb_hit +1 where qb_num = ?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, num);
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(null,ps,con);
				}
			}
			
			//Board Delete 원본글
			public void deleteBoard(int ref, int depth) {
				Connection con = null;
				PreparedStatement ps = null;
				String sql = null;
				try {				
					con = db.getConnection();
					sql = "delete from qboard where qb_ref=? and qb_depth>=?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, ref);
					ps.setInt(2, depth);
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(null,ps,con);
				}
			}
			
			//Board Delete 답글
					public void deletereply(int num) {
						Connection con = null;
						PreparedStatement ps = null;
						String sql = null;
						try {
							
							con = db.getConnection();
							sql = "delete from qboard where qb_num=?";
							ps = con.prepareStatement(sql);
							ps.setInt(1, num);
							ps.executeUpdate();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							db.closeConnection(null,ps,con);
						}
					}
			
			
			
			
			//Board Reply: 답변글 저장
			public void replyBoard(QboardDto bean) {
				Connection con = null;
				PreparedStatement ps = null;
				String sql = null;
				try {
					con = db.getConnection();
					sql = "insert qboard(qb_writer,qb_content,qb_title,qb_ref,qb_pos,qb_depth,"
							+ "qb_hit)values(?, ?, ?, ?, ?, ?, 0)";
					ps = con.prepareStatement(sql);
					ps.setString(1, bean.getWriter());
					ps.setString(2, bean.getContent());
					ps.setString(3, bean.getTitle());
					///////////////////////////////////
					ps.setInt(4, bean.getRef());//원글과 동일한 qb_ref
					ps.setInt(5, bean.getPos()+1);//원글의 qb_pos+1
					ps.setInt(6, bean.getDepth()+1);//원글의 qb_depth+1
					///////////////////////////////////			
					
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(null,ps,con);
				}
			}
			
			//Board Reply Up : 답변글 위치값 조정 
			//답변하고자는 게시물의 qb_pos 값보다 큰 값들을 1씩 증가
			public void replyUpBoard(int qb_ref, int qb_pos) {
				Connection con = null;
				PreparedStatement ps = null;
				String sql = null;
				try {
					con = db.getConnection();
					sql = "update qboard set qb_pos=qb_pos+1 where qb_ref=? and qb_pos>?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, qb_ref);
					ps.setInt(2, qb_pos);
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(null,ps,con);
				}
			}
			
			// 유저가 쓴글 목록
			public ArrayList<QboardDto> userList(String nick) 
			{
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				ArrayList<QboardDto> arr= new ArrayList<QboardDto>();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String today = sdf.format(cal.getTime());

				String sql = "select * from qboard where qb_writer=?";

				try {
					con = db.getConnection();
					ps = con.prepareStatement(sql);
					ps.setString(1, nick);
					rs = ps.executeQuery();

					while(rs.next()) 
					{
						QboardDto dto = new QboardDto();

						dto.setTitle(rs.getString("qb_title"));
						dto.setWriter(rs.getString("qb_writer"));
						dto.setHit(rs.getInt("qb_hit"));
						dto.setNum(rs.getInt("qb_num"));

						String Date = rs.getTimestamp("qb_date").toString();
						if(Date.substring(0, 10).equals(today)) {
							dto.setDate(Date.substring(11,19));
						}else {
							dto.setDate(Date.substring(0,10));
						}				
											
						arr.add(dto);
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

				return arr;
			}
			
			//유저 작성한 글 개수
			public int userBoardCount(String nick) {
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = null;
				int cnt = 0;
				try {
					con = db.getConnection();
										
					sql = "select count(*) from cboard where cb_writer=? union all "
						+"select count(*) from pboard where pb_writer=? union all "
						+"select count(*) from qboard where qb_writer=?;";	
					ps = con.prepareStatement(sql);
					ps.setString(1, nick);
					ps.setString(2, nick);
					ps.setString(3, nick);
					
					rs = ps.executeQuery();
					while(rs.next()) {
						cnt += rs.getInt(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(rs,ps,con);
				}
				return cnt;
			}
			
			//Board List : 페이지당 보여줄 갯수만 리턴, 검색 포함.
			//keyField : 선택옵션(name, subject, content)
			//keyWord : 검색어
			//start : 시작번호, cnt : 한 페이지당 가져올 게시물 개수 
			public ArrayList<QboardDto> getBoardList(String keyField,
					String keyWord,int start, int cnt){
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = null;
				ArrayList<QboardDto> arr = new ArrayList<QboardDto>();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String today = sdf.format(cal.getTime());
				
				try {
					con = db.getConnection();
					
						
						sql = "select * from qboard order by qb_ref desc, qb_pos "
									+ "limit ?,?";												
						ps = con.prepareStatement(sql);					
						ps.setInt(1, start);
						ps.setInt(2, cnt);
			
					
					rs = ps.executeQuery();
					while(rs.next()) {					
						QboardDto bean = new QboardDto();
						bean.setNum(rs.getInt("qb_num"));
						bean.setWriter(rs.getString("qb_writer"));
						bean.setTitle(rs.getString("qb_title"));
						bean.setPos(rs.getInt("qb_pos"));
						bean.setRef(rs.getInt("qb_ref"));
						bean.setDepth(rs.getInt("qb_depth"));
						
						String Date = rs.getTimestamp("qb_date").toString();
						if(Date.substring(0, 10).equals(today)) {
							bean.setDate(Date.substring(11,19));
						}else {
							bean.setDate(Date.substring(0,10));
						}
						
						bean.setHit(rs.getInt("qb_hit"));	
						arr.add(bean);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(rs,ps,con);
				}
				return arr;
			}
			
			//검색시 답글까지 다 가져오기
			public ArrayList<QboardDto> getBoardList(int ref,int start, int cnt){
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = null;
				ArrayList<QboardDto> arr = new ArrayList<QboardDto>();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String today = sdf.format(cal.getTime());
				
				try {
					con = db.getConnection();
					
						sql = "select * from qboard where qb_ref=? order by qb_ref desc, qb_pos "
									+ "limit ?,?";												
						ps = con.prepareStatement(sql);					
						ps.setInt(1, ref);
						ps.setInt(2, start);
						ps.setInt(3, cnt);
					
					
					rs = ps.executeQuery();
					while(rs.next()) {					
						QboardDto bean = new QboardDto();
						bean.setNum(rs.getInt("qb_num"));
						bean.setWriter(rs.getString("qb_writer"));
						bean.setTitle(rs.getString("qb_title"));
						bean.setPos(rs.getInt("qb_pos"));
						bean.setRef(rs.getInt("qb_ref"));
						bean.setDepth(rs.getInt("qb_depth"));
						
						// 오늘날짜면 시간만
						String Date = rs.getTimestamp("qb_date").toString();
						if(Date.substring(0, 10).equals(today)) {
							bean.setDate(Date.substring(11,19));
						}else {
							bean.setDate(Date.substring(0,10));
						}
						
						bean.setHit(rs.getInt("qb_hit"));	
						arr.add(bean);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(rs,ps,con);
				}
				return arr;
			}
			
			//검색시 ref 값들 가져오기
			public ArrayList<Integer> getRefList(String keyField, String keyWord){
				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String sql = null;
				ArrayList<Integer> arr = new ArrayList<Integer>();
				
				try {
					con = db.getConnection();
					
						sql = "select * from qboard where "+keyField+" like '%"+keyWord+"%' group by qb_ref order by qb_ref desc, qb_pos;";								
						ps = con.prepareStatement(sql);				
						
					rs = ps.executeQuery();
					while(rs.next()) {
							
						arr.add(rs.getInt("qb_ref"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					db.closeConnection(rs,ps,con);
				}
				return arr;
			}

}
