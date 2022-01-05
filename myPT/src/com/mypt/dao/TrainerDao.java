package com.mypt.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.mypt.connection.DBConnection;
import com.mypt.dto.TrainerDto;
import com.mypt.dto.UserDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class TrainerDao {
	
	private DBConnection db;
	
	
//////////dao 싱글톤 (이)
private static TrainerDao instance = new TrainerDao();
public static TrainerDao getInstance() 
{
return instance;
}
	
	private TrainerDao() {
		db = DBConnection.getInstance();
	}

	
	//사진 등록 추가
	public void insertTrainer(HttpServletRequest request) {

		String encoding = "UTF-8";
		int maxSize= 2*1024*1024; //2MB
		
//		절대경로로 해당 path를 지정 할 경우
//		String saveFolder = "C:/myPTImages/TrainerPhoto/";
		
		String saveFolder= request.getServletContext().getRealPath("img/TrainerPhoto/");  
//서버 옵션에서 serve modules without publishing. 현재 컴 기준  
//E:\Work\ProjectA\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\myPT\img\TrainerPhoto\

//serve modules without publishing 체크 //체크 여부 상관 없이 배포한 것을 실행할 땐 아래의 경로로 인식됨.
//C:\Users\admin\git\teamA\myPT\WebContent\img\TrainerPhoto\		
								
		Connection con = null;
		PreparedStatement ps = null;
		
		byte[] byteArr = new byte[1024];
        FileInputStream fin = null;
        FileOutputStream fout = null;
        int read= 0;
		
		try {
			con = db.getConnection();
			String sql = "insert into trainer(t_id,t_pw,t_name,t_gender,t_email,t_birth,t_address,t_nick,t_zipcode,t_tel,t_addrdetail,t_photo) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);

			File dir = new File(saveFolder);			
			if(!dir.exists()) //디렉터리 없으면 생성
			{
				org.apache.commons.io.FileUtils.forceMkdir(dir);
			}
			
			MultipartRequest multi = 
					new MultipartRequest(request, saveFolder, maxSize,
							encoding, new DefaultFileRenamePolicy());
			
			String t_photo = multi.getFilesystemName("photo"); //input태그-> 서버상에 실제로 업로드된 파일 이름
			File curFile = multi.getFile("photo");  //실제 업로드된 파일 객체 반환

			String t_Id= makeID(); //트레이너 아이디 생성			
			
			//파일이름 t_ID
			String newFileName= t_Id+"."+t_photo.substring(t_photo.lastIndexOf(".")+1);
	        File newFile = new File(saveFolder + newFileName);
	        
	        System.out.println(t_photo);
			System.out.println(t_Id);
			System.out.println(newFileName);

	        // 파일명 rename
	        if(!curFile.renameTo(newFile)){
	            // rename이 되지 않을경우 강제로 파일을 복사하고 기존파일은 삭제
	            byteArr = new byte[maxSize];
	            fin = new FileInputStream(curFile);
	            fout = new FileOutputStream(newFile);
	            while((read=fin.read(byteArr,0,byteArr.length))!=-1)
	            {
	                fout.write(byteArr, 0, read);
	            }
	             
	            fin.close();
	            fout.close();
	            curFile.delete();
	        }  
	        	
			ps.setString(1, t_Id);
			ps.setString(2, multi.getParameter("password"));
			ps.setString(3, multi.getParameter("userName"));
			ps.setString(4, multi.getParameter("gender"));
			ps.setString(5, multi.getParameter("email"));
			ps.setString(6, multi.getParameter("birthdate"));
			ps.setString(7, multi.getParameter("address"));
			ps.setString(8, multi.getParameter("nickname"));
			ps.setString(9, multi.getParameter("zipcode"));
			ps.setString(10, multi.getParameter("tel"));
			ps.setString(11, multi.getParameter("addrdetail"));		
			
//			ps.setString(12, saveFolder+t_photo); // 경로 모두 포함해서 db에 저장
			ps.setString(12, newFileName); //파일명만 db에 저장
				
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
	

	public TrainerDto trainerSelect(String t_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TrainerDto td = new TrainerDto();
		try {
			con = db.getConnection();
			String sql = "select * from trainer where t_id='" + t_id + "'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				td.setT_id(rs.getString("t_id"));
				td.setT_pw(rs.getString("t_pw"));
				td.setT_name(rs.getString("t_name"));
				td.setT_gender(rs.getString("t_gender"));
				td.setT_email(rs.getString("t_email"));
				td.setT_birth(rs.getString("t_birth"));
				td.setT_address(rs.getString("t_address"));
				td.setT_nick(rs.getString("t_nick"));
				td.setT_zipcode(rs.getString("t_zipcode"));
				td.setT_tel(rs.getString("t_tel"));
				td.setT_addrdetail(rs.getString("t_addrdetail"));
				td.setT_photo(rs.getString("t_photo"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		return td;
	}
	
	public void trainerUpdate(TrainerDto td) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = db.getConnection();			
				String sql = "update trainer set t_pw=?, t_name=?, t_gender=?, t_email=?, t_birth=?, t_address=?, t_nick=?,t_zipcode=?, t_tel=?, t_addrdetail=? where t_id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, td.getT_pw());
				ps.setString(2, td.getT_name());
				ps.setString(3, td.getT_gender());
				ps.setString(4, td.getT_email());
				ps.setString(5, td.getT_birth());
				ps.setString(6, td.getT_address());
				ps.setString(7, td.getT_nick());
				ps.setString(8, td.getT_zipcode());
				ps.setString(9, td.getT_tel());
				ps.setString(10, td.getT_addrdetail());
				ps.setString(11, td.getT_id());
				ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	public void trainerPhotoUpdate(TrainerDto td) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = db.getConnection();			
				String sql = "update trainer set t_pw=?, t_name=?, t_gender=?, t_email=?, t_birth=?, t_address=?, t_nick=?,t_zipcode=?, t_tel=?, t_addrdetail=?,t_photo=? where t_id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, td.getT_pw());
				ps.setString(2, td.getT_name());
				ps.setString(3, td.getT_gender());
				ps.setString(4, td.getT_email());
				ps.setString(5, td.getT_birth());
				ps.setString(6, td.getT_address());
				ps.setString(7, td.getT_nick());
				ps.setString(8, td.getT_zipcode());
				ps.setString(9, td.getT_tel());
				ps.setString(10, td.getT_addrdetail());
				ps.setString(11, td.getT_photo());
				ps.setString(12, td.getT_id());
				ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}
	
	public void trainerUpdate(HttpServletRequest request,String prevphoto) {
		String encoding = "UTF-8";
		int maxSize= 2*1024*1024; //2MB
		
		String saveFolder= request.getServletContext().getRealPath("img/TrainerPhoto/");  
//서버 옵션에서 serve modules without publishing. 현재 컴 기준  
//E:\Work\ProjectA\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\myPT\img\TrainerPhoto\

//serve modules without publishing 체크 //체크 여부 상관 없이 배포한 것을 실행할 땐 아래의 경로로 인식됨.
//C:\Users\admin\git\teamA\myPT\WebContent\img\TrainerPhoto\		
								
		Connection con = null;
		PreparedStatement ps = null;
		
		byte[] byteArr = new byte[1024];
        FileInputStream fin = null;
        FileOutputStream fout = null;
        int read= 0;		
		
		try {
			con = db.getConnection();
			
				String sql = "update trainer set t_pw=?, t_name=?, t_gender=?, t_email=?, t_birth=?, t_address=?, t_nick=?,t_zipcode=?, t_tel=?, t_addrdetail=?, t_photo=? where t_id=?";
				
				File dir = new File(saveFolder);			
				if(!dir.exists()) //디렉터리 없으면 생성
				{
					org.apache.commons.io.FileUtils.forceMkdir(dir);
				}
				
				MultipartRequest multi = 
						new MultipartRequest(request, saveFolder, maxSize,
								encoding, new DefaultFileRenamePolicy());
				
				File prevFile = new File(saveFolder+prevphoto);
				System.out.println(saveFolder+prevphoto);
				if(prevFile.exists()) {
					prevFile.delete();
				}
			
				
				String t_photo = multi.getFilesystemName("photo"); //input태그-> 서버상에 실제로 업로드된 파일 이름
				File curFile = multi.getFile("photo");  //실제 업로드된 파일 객체 반환
	
				
				String t_Id = multi.getParameter("hiddentrainerID");				
				//파일이름 t_ID
				String newFileName= t_Id+"."+t_photo.substring(t_photo.lastIndexOf(".")+1);
		        File newFile = new File(saveFolder + newFileName);
		        
		        

		        // 파일명 rename
		        if(!curFile.renameTo(newFile)){
		            // rename이 되지 않을경우 강제로 파일을 복사하고 기존파일은 삭제
		            byteArr = new byte[maxSize];
		            fin = new FileInputStream(curFile);
		            fout = new FileOutputStream(newFile);
		            while((read=fin.read(byteArr,0,byteArr.length))!=-1)
		            {
		                fout.write(byteArr, 0, read);
		            }
		             
		            fin.close();
		            fout.close();
		            curFile.delete();
		        }  				
				
				ps = con.prepareStatement(sql);
				ps.setString(1, multi.getParameter("password"));
				ps.setString(2, multi.getParameter("trainerName"));
				ps.setString(3, multi.getParameter("gender"));
				ps.setString(4, multi.getParameter("email"));
				ps.setString(5, multi.getParameter("birthdate"));
				ps.setString(6, multi.getParameter("address"));
				ps.setString(7, multi.getParameter("nickname"));
				ps.setString(8, multi.getParameter("zipcode"));
				ps.setString(9, multi.getParameter("tel"));
				ps.setString(10, multi.getParameter("addrdetail"));
				ps.setString(11, newFileName);
				ps.setString(12, multi.getParameter("hiddentrainerID"));
				ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}
	}

	public void trainerDelete(String t_id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = db.getConnection();
			String sql = "delete from trainer where t_id = '" + t_id + "'";
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(null, ps, con);
		}

	}
	
	// 트레이너 리스트 보기
	public ArrayList<TrainerDto> trainerList(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<TrainerDto> arr = new ArrayList<TrainerDto>();
		TrainerDto td = null;
		try {
			con = db.getConnection();
			String sql = "select * from trainer";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				td = new TrainerDto();
				td.setT_id(rs.getString("t_id"));
				td.setT_pw(rs.getString("t_pw"));
				td.setT_name(rs.getString("t_name"));
				td.setT_gender(rs.getString("t_gender"));
				td.setT_email(rs.getString("t_email"));
				td.setT_birth(rs.getString("t_birth"));
				td.setT_address(rs.getString("t_address"));
				td.setT_nick(rs.getString("t_nick"));
				td.setT_addrdetail(rs.getString("t_addrdetail"));
				td.setT_zipcode(rs.getString("t_zipcode"));
				td.setT_tel(rs.getString("t_tel"));
				arr.add(td);
			}
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.closeConnection(rs, ps, con);
		}
		
		return arr;
	}
	
	//트레이너 로그인
	public int trainerLogin(String id, String pw) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=null;
		int flag = 0;
		
		
		try {
			con=db.getConnection();
			sql="select * from trainer where t_id=? and t_pw=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs=ps.executeQuery();
			if(rs.next()) {
				flag = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	
	// 트레이너  수 세기 (이)
		public int countTrainer() {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			int result = 2;

			String sql = "select count(*) from trainer";
			try {
				con = db.getConnection();
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();

				if (rs.next()) 
				{
					result = rs.getInt(1);
					System.out.println(result);
				} else 
				{
					result = 0;
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

			return result;
		}
		
		
	
//	트레이너 아이디 생성. T+000포맷+1) (이)
	public String makeID() {
		SimpleDateFormat sf = new SimpleDateFormat("yyMM");
		Date today = new Date();

		String signYearAndMonth = sf.format(today);

		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM");
		String signYearAndMonth2 = sf2.format(today);
		

		return "T"+String.format("%04d", countTrainer() + 1);

	}
	
	
	public void uploadTrainerPhoto()
	{
		
	}
	
	// 트레이너가 수정
	public void trainerMyUpdate(TrainerDto td) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = db.getConnection();
			String sql = "update trainer set t_pw=?, t_email=?, t_address=?, t_zipcode=?, t_tel=?, t_addrdetail=? where t_id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, td.getT_pw());
			ps.setString(2, td.getT_email());			
			ps.setString(3, td.getT_address());			
			ps.setString(4, td.getT_zipcode());
			ps.setString(5, td.getT_tel());
			ps.setString(6, td.getT_addrdetail());
			ps.setString(7, td.getT_id());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

			String sql = "update trainer set sessionId=?, cookieEnd=? where t_id=?";
			try {
				con = db.getConnection();
				ps = con.prepareStatement(sql);

				ps.setString(1, sessionId);
				ps.setTimestamp(2, cookieEnd);
				ps.setString(3, id);

				result= ps.executeUpdate();
				System.out.println("트레이너 쿠키 result:"+ result);

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
		
		public TrainerDto checkSessionId(String sessionId)  //세션아이디로 값 가져오기
		{
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			TrainerDto td= null;

			String sql = "select * from trainer where sessionId=? and cookieEnd>now()";

			try {
				con = db.getConnection();
				ps = con.prepareStatement(sql);
				
				ps.setString(1, sessionId);
				
				rs = ps.executeQuery();

				if(rs.next()) 
				{
					td = new TrainerDto();
					
					td.setT_id(rs.getString("t_id"));
					td.setT_pw(rs.getString("t_pw"));
					td.setT_name(rs.getString("t_name"));
					td.setT_gender(rs.getString("t_gender"));
					td.setT_email(rs.getString("t_email"));
					td.setT_birth(rs.getString("t_birth"));
					td.setT_address(rs.getString("t_address"));
					td.setT_nick(rs.getString("t_nick"));
					td.setT_zipcode(rs.getString("t_zipcode"));
					td.setT_tel(rs.getString("t_tel"));
					td.setT_addrdetail(rs.getString("t_addrdetail"));
					td.setT_photo(rs.getString("t_photo"));
					
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
			
			return td;

		}
	

}
