package com.mypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.mypt.connection.DBConnection;
import com.mypt.dto.UserDto;

public class UserDao {
	private DBConnection db;

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	private UserDao() {
		db = DBConnection.getInstance();
	}

	public void insertUser(UserDto userBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;


		try {
			con = db.getConnection();
			sql = "insert user(id,pw,name,gender,email,birth,"
					+ "address,qr,nick,startdate,enddate,"
					+ "ptcount,tid,zipcode,tel,addrdetail) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			String gender = userBean.getGender();
			String newUserID = makeID(gender, userBean.getBirth().substring(2, 4));
	
			ps.setString(1, newUserID);	
			ps.setString(2, userBean.getPw());
			ps.setString(3, userBean.getName());
			ps.setString(4, gender);
			ps.setString(5, userBean.getEmail());
			ps.setString(6, userBean.getBirth());
			ps.setString(7, userBean.getAddress());
			ps.setString(8, userBean.getQr());
			ps.setString(9, userBean.getNick());				
			ps.setString(10, userBean.getStartdate());
			ps.setString(11, userBean.getEnddate());			
			ps.setInt(12, userBean.getPtcount());
			ps.setString(13, userBean.getTid());
			ps.setString(14, userBean.getZipcode());
			ps.setString(15, userBean.getTel());
			ps.setString(16, userBean.getAddrdetail());

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

	public UserDto getUserById(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		UserDto userBean = new UserDto();

		try {
			con = db.getConnection();
			sql = "select * from user where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				userBean.setId(rs.getString("id"));
				userBean.setPw(rs.getString("pw"));
				userBean.setName(rs.getString("name"));
				userBean.setGender(rs.getString("gender"));
				userBean.setEmail(rs.getString("email"));
				userBean.setBirth(rs.getString("birth"));
				userBean.setAddress(rs.getString("address"));
				userBean.setQr(rs.getString("qr"));
				userBean.setSigndate(rs.getTimestamp("signdate"));
				userBean.setNick(rs.getString("nick"));			
				userBean.setStartdate(rs.getString("startdate"));
				userBean.setEnddate(rs.getString("enddate"));				
				userBean.setPtcount(rs.getInt("ptcount"));
				userBean.setTid(rs.getString("tid"));
				userBean.setTel(rs.getString("tel"));
				userBean.setZipcode(rs.getString("zipcode"));
				userBean.setAddrdetail(rs.getString("addrdetail"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return userBean;
	}

	// 유저가 수정
	public void updateUser(UserDto userBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "update user set pw=?, zipcode=?,address=?,addrdetail=?,tel=? where email=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, userBean.getPw());
			ps.setString(2, userBean.getZipcode());
			ps.setString(3, userBean.getAddress());
			ps.setString(4, userBean.getAddrdetail());
			ps.setString(5, userBean.getTel());
			ps.setString(6, userBean.getEmail());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);

		}

	}

	// 관리자가 수정
	public void manageUser(UserDto userBean) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "update user set pw=?,name=?,gender=?,email=?," + "birth=?,address=?,nick=?,"
					+ "startdate=?,enddate=?,ptcount=?,tid=?,zipcode=?,tel=?,addrdetail=? " + " where id=?";
			ps = con.prepareStatement(sql);

			ps.setString(1, userBean.getPw());
			ps.setString(2, userBean.getName());
			ps.setString(3, userBean.getGender());
			ps.setString(4, userBean.getEmail());
			ps.setString(5, userBean.getBirth());
			ps.setString(6, userBean.getAddress());
			ps.setString(7, userBean.getNick());
			
			ps.setString(8, userBean.getStartdate());
			ps.setString(9, userBean.getEnddate());
			
			ps.setInt(10, userBean.getPtcount());
			ps.setString(11, userBean.getTid());
			ps.setString(12, userBean.getZipcode());
			ps.setString(13, userBean.getTel());
			ps.setString(14, userBean.getAddrdetail());
			ps.setString(15, userBean.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	
	
	//enddate가 지난 회원 전부에 대해 startdate, enddate, ptcount 설정(이)
	public void updateForEnddateUser() 
	{
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "update user SET startdate=NULL, enddate= NULL, ptcount=0 WHERE enddate<curdate()";
			ps = con.prepareStatement(sql);
			
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
	
	// 결제 후 (이)
		public void updateAfterPayment(UserDto userBean) 
		{
			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;

			try {
				con = db.getConnection();
				sql = "update user set startdate=?,enddate=?,ptcount=ptcount+?, tid=? where id=?";
				ps = con.prepareStatement(sql);
		
				ps.setString(1, userBean.getStartdate());
				ps.setString(2, userBean.getEnddate());			
				ps.setInt(3, userBean.getHistory().getHcount());
				ps.setString(4, userBean.getTid());
				ps.setString(5, userBean.getId());
				
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
		
	
	

	public void deleteUser(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			con = db.getConnection();
			sql = "delete from user where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

// 로그인 시 사용자 정보 받아오는 용도
	public UserDto getUserByEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		UserDto userBean = new UserDto();

		try {
			con = db.getConnection();
			sql = "select * from user where email=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				userBean.setId(rs.getString("id"));
				userBean.setPw(rs.getString("pw"));
				userBean.setName(rs.getString("name"));
				userBean.setGender(rs.getString("gender"));
				userBean.setEmail(rs.getString("email"));
				userBean.setBirth(rs.getString("birth"));
				userBean.setAddress(rs.getString("address"));
				userBean.setQr(rs.getString("qr"));
				userBean.setSigndate(rs.getTimestamp("signdate"));
				userBean.setNick(rs.getString("nick"));

				userBean.setStartdate(rs.getString("startdate"));
				userBean.setEnddate(rs.getString("enddate"));
				
				userBean.setTid(rs.getString("tid"));
				userBean.setZipcode(rs.getString("zipcode"));
				userBean.setTel(rs.getString("tel"));
				userBean.setPtcount(rs.getInt("ptcount"));
				userBean.setAddrdetail(rs.getString("addrdetail"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, con);
		}

		return userBean;
	}

	// 전체리스트
	public ArrayList<UserDto> userList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<UserDto> arr = new ArrayList<UserDto>();

		try {
			con = db.getConnection();
			sql = "select * from user a " + "left outer join trainer b " + "on a.tid=b.t_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserDto userBean = new UserDto();
				userBean.setId(rs.getString("id"));
				userBean.setPw(rs.getString("pw"));
				userBean.setName(rs.getString("name"));		
				userBean.setGender(rs.getString("gender"));
				userBean.setEmail(rs.getString("email"));

				// 나이 계산
				SimpleDateFormat format = new SimpleDateFormat("yyyy");
				Date year = new Date();
				String thisYear = format.format(year);
				int age = (Integer.parseInt(thisYear.toString())
						- Integer.parseInt(rs.getString("birth").substring(0, 4)) + 1);
				userBean.setBirth(String.valueOf(age));

				userBean.setAddress(rs.getString("address"));
				userBean.setQr(rs.getString("qr"));
				userBean.setSigndate(rs.getTimestamp("signdate"));
				userBean.setNick(rs.getString("nick"));
				
				userBean.setStartdate(rs.getString("startdate"));
				userBean.setEnddate(rs.getString("enddate"));
				
				userBean.setPtcount(rs.getInt("ptcount"));
				userBean.setTid(rs.getString("t_name"));
				userBean.setZipcode(rs.getString("zipcode"));
				userBean.setTel(rs.getString("tel"));
				userBean.setAddrdetail(rs.getString("addrdetail"));

				arr.add(userBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	
	
	// 트레이너 아이디별 유저 검색
	public ArrayList<UserDto> ptUserList(String tid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<UserDto> arr = new ArrayList<UserDto>();

		try {
			con = db.getConnection();
			sql = "select * from user where tid=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, tid);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserDto userBean = new UserDto();
				userBean.setId(rs.getString("id"));
				userBean.setPw(rs.getString("pw"));
				userBean.setName(rs.getString("name"));

				// 성별 구분
//				String gender = "";
//				if (rs.getString("gender").equals("M")) {
//					gender = "남자";
//				} else {
//					gender = "여자";
//				}
				userBean.setGender(rs.getString("gender"));

				userBean.setEmail(rs.getString("email"));

				// 나이 계산
				SimpleDateFormat format = new SimpleDateFormat("yyyy");
				Date year = new Date();
				String thisYear = format.format(year);
				int age = (Integer.parseInt(thisYear.toString())
						- Integer.parseInt(rs.getString("birth").substring(0, 4)) + 1);
				userBean.setBirth(String.valueOf(age));

				userBean.setAddress(rs.getString("address"));
				userBean.setQr(rs.getString("qr"));
				userBean.setSigndate(rs.getTimestamp("signdate"));
				userBean.setNick(rs.getString("nick"));

				userBean.setStartdate(rs.getString("startdate"));
				userBean.setEnddate(rs.getString("enddate"));
				
				userBean.setPtcount(rs.getInt("ptcount"));
				userBean.setTid(rs.getString("tid"));
				userBean.setTel(rs.getString("tel"));
				userBean.setZipcode(rs.getString("zipcode"));
				userBean.setAddrdetail(rs.getString("addrdetail"));

				arr.add(userBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return arr;
	}

	// 로그인 확인
	public int userLogin(String email, String pw) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int flag = 0;

		try {
			con = db.getConnection();
			sql = "select * from user where email=? and pw=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if (rs.next()) {
				flag = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.closeConnection(rs, ps, con);
		}

		return flag;
	}

//	성별+생년+가입년월+000(해당 가입년월에 가입한 회원 수 +1) (이)
	public String makeID(String gender, String birthYear) {
		SimpleDateFormat sf = new SimpleDateFormat("yyMM");
		Date today = new Date();

		String signYearAndMonth = sf.format(today);

		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");
		String signYearAndMonth2 = sf2.format(today);
		
		String genderInit = "";
		
		if(gender.equals("남성"))
		{
			genderInit= "M";
		}
		else
		{
			genderInit= "F";
		}

		return genderInit + birthYear + signYearAndMonth + String.format("%03d", countUser(signYearAndMonth2) + 1);

	}

//	닉네임 중복체크(이)
	public int checkNick(String nickName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Integer result = null;

		String sql = "select nick from user WHERE nick=? UNION select t_nick from trainer WHERE t_nick=?";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, nickName);
			ps.setString(2, nickName);

			rs = ps.executeQuery();

			if (rs.next()) {
				result = 1;
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

// 이메일 중복 체크 (이)
	public int checkEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Integer result = null;

		String sql = "select email from user WHERE email=? UNION select t_email from trainer WHERE t_email=?";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, email);

			rs = ps.executeQuery();

			if (rs.next()) {
				result = 1;
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

// 회원 수 세기 (해당 가입 년월) (이)
	public int countUser(String signdate) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int result = 2;

		String sql = "select count(*) from user where signdate>=? and signdate<=? ";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			Calendar cal = Calendar.getInstance();

			ps.setString(1, signdate + "-01");
			ps.setString(2, signdate + "-" + cal.getActualMaximum(cal.DAY_OF_MONTH));

			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
				System.out.println(result);
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

	// QR 값을 받아오기
	public String getQR(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=null;
		
		String result = null;

		try {
			sql= "select qr from user where id=?";
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1,id);
			rs = ps.executeQuery();

			if (rs.next()) {
				result = rs.getString("qr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}

		return result;
	}
	
	// PT 완료
		public void ptFinish(String id) {
			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;

			try {
				con = db.getConnection();
				sql = "update user set ptcount=ptcount-1 where id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, id);		

				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.closeConnection(null, ps, con);

			}

		}
	
	
	//세션id, 유효시간 저장(쿠키)
	public int keepLogin(String id, String sessionId, Timestamp cookieEnd)
	{
		Connection con = null;
		PreparedStatement ps = null;
		
		int result=0;

		String sql = "update user set sessionId=?, cookieEnd=? where id=?";
		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, sessionId);
			ps.setTimestamp(2, cookieEnd);
			ps.setString(3, id);

			result= ps.executeUpdate();
			System.out.println("쿠키 result:"+ result);

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
	
	public UserDto checkSessionId(String sessionId)  //세션아이디로 값 가져오기
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserDto dto= null;

		String sql = "select * from user where sessionId=? and cookieEnd>now()";

		try {
			con = db.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, sessionId);
			
			rs = ps.executeQuery();

			if(rs.next()) 
			{
				dto = new UserDto();
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setBirth(rs.getString("birth"));
				dto.setAddress(rs.getString("address"));
				dto.setQr(rs.getString("qr"));
				dto.setSigndate(rs.getTimestamp("signdate"));
				dto.setNick(rs.getString("nick"));

				dto.setStartdate(rs.getString("startdate"));
				dto.setEnddate(rs.getString("enddate"));
				
				dto.setTid(rs.getString("tid"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setTel(rs.getString("tel"));
				dto.setPtcount(rs.getInt("ptcount"));
				dto.setAddrdetail(rs.getString("addrdetail"));
				
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
