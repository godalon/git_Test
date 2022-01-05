package com.mypt.action.detail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mypt.controller.Action;
import com.mypt.dao.TrainerDao;
import com.mypt.dto.TrainerDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminTrainerUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TrainerDao dao = TrainerDao.getInstance();
		TrainerDto dto = new TrainerDto();
		
		String encoding = "UTF-8";
		int maxSize= 2*1024*1024; //2MB
		
		String saveFolder= request.getServletContext().getRealPath("img/TrainerPhoto/");
		
		
		byte[] byteArr = new byte[1024];
        FileInputStream fin = null;
        FileOutputStream fout = null;
        int read= 0;	
        
		MultipartRequest multi = 
				new MultipartRequest(request, saveFolder, maxSize,
						encoding, new DefaultFileRenamePolicy());
		
		  				
		
		System.out.println("사진값 널인가?"+multi.getFilesystemName("photo"));
		//사진 수정 시
		if(multi.getFilesystemName("photo")!=null) {
			
			 File dir = new File(saveFolder);			
			if(!dir.exists()) //디렉터리 없으면 생성
			{
				org.apache.commons.io.FileUtils.forceMkdir(dir);
			}
			
			//이전파일 삭제
			String prevphoto = multi.getParameter("prevphoto");
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
	        
	        dto.setT_name(multi.getParameter("trainerName"));
			dto.setT_id(multi.getParameter("hiddentrainerID"));
			dto.setT_gender(multi.getParameter("gender"));
			dto.setT_birth(multi.getParameter("birthdate"));
			dto.setT_nick(multi.getParameter("nickname"));
			dto.setT_email(multi.getParameter("email"));
			dto.setT_tel(multi.getParameter("tel"));
			dto.setT_pw(multi.getParameter("password"));
			dto.setT_zipcode(multi.getParameter("zipcode"));
			dto.setT_address(multi.getParameter("address"));
			dto.setT_addrdetail(multi.getParameter("addrdetail"));
			dto.setT_photo(newFileName);
	        
		        			
			dao.trainerPhotoUpdate(dto);
			
		}else {
			
						
			dto.setT_name(multi.getParameter("trainerName"));
			dto.setT_id(multi.getParameter("hiddentrainerID"));
			dto.setT_gender(multi.getParameter("gender"));
			dto.setT_birth(multi.getParameter("birthdate"));
			dto.setT_nick(multi.getParameter("nickname"));
			dto.setT_email(multi.getParameter("email"));
			dto.setT_tel(multi.getParameter("tel"));
			dto.setT_pw(multi.getParameter("password"));
			dto.setT_zipcode(multi.getParameter("zipcode"));
			dto.setT_address(multi.getParameter("address"));
			dto.setT_addrdetail(multi.getParameter("addrdetail"));
						
			
			dao.trainerUpdate(dto);
		}
		
		
		
		return "redirect:trainerList.do";
	}

}
