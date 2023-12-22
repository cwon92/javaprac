package com.spring5.mypro00.common.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

//@Controller
public class FileUploadAjaxControllerV3 {
	
	private String uploadFileRepoDir = "C:/myupload" ;
	
	//form을 통한 다중 파일 업로드  //uploadFiles
	
	//1. 파일 업로드 요청 JSP 페이지 호출
	
	//@GetMapping(value= {"/fileUploadAjax"})
	public String callFileUploadFormPage() {
		System.out.println("'Ajax를 통한 업로드 테스트' JSP 페이지 호출======== ");
		return "sample/fileUploadAjax";
		
	}
	//이미지파일에 대한 썸네일이미지 저장
	//Step1: 업로드파일에 대한 이미지 파일여부 검사 메소드
	private boolean isImageFile(File yourFile) {
		
		String yourFileContentType = null;
		try {
			yourFileContentType = Files.probeContentType(yourFile.toPath());
			System.out.println("fileContentType: " + yourFileContentType);
			
			return yourFileContentType.startsWith("image");
		} catch (IOException e) {
			System.out.println("오류: " + e.getMessage());
		}
		
		return false ;
	}

	//날짜형식 문자열 생성 메서드
	private String getDateFmtPathName() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd") ;
		String dateFmtStr = sdf.format(new Date());
		
		return dateFmtStr ;

	}
	
	//2. 파일 업로드 처리
	//@PostMapping(value = "/fileUploadAjaxAction")
	@ResponseBody
	public String fileUploadActionForm(MultipartFile[] yourUploadFiles) {
		
		String fileName = null ;
		
		//UUID를 이용한 고유한 파일이름 적용
		String myUuid = null ;
		
		//날짜 형식 폴더 구조 생성
		String dateFmtStr = getDateFmtPathName() ;
		
		File fileUploadPath = new File(uploadFileRepoDir, dateFmtStr);
//		 C:/myupload\2023/12/14
		if (!fileUploadPath.exists()) {
			fileUploadPath.mkdirs() ;
		}
		
		for(MultipartFile uploadFile : yourUploadFiles) {
			System.out.println("=============================");
			System.out.println("Upload File Name: " + uploadFile.getOriginalFilename());
			System.out.println("Upload File Size: " + uploadFile.getSize());
			
			fileName = uploadFile.getOriginalFilename();
			// 파일이름.확장자, 경로명\파일이름.확장자, 파일이름만 남기는 처리.
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1) ;
			
			//UUID를 이용한 고유한 파일이름 적용
			myUuid = UUID.randomUUID().toString() ;
			
			fileName = myUuid + "_" + fileName ;
			
			File saveUploadFile = new File(fileUploadPath, fileName);
			
			try {
				uploadFile.transferTo(saveUploadFile);
				
				//이미지파일이면 썸네일 생성
				if(isImageFile(saveUploadFile)) {
					
					File thumbnameFile = new File(fileUploadPath, "s_" + fileName);
					
					FileOutputStream myFos = 
							new FileOutputStream(thumbnameFile) ;
							
					
					InputStream myIs = uploadFile.getInputStream() ;
					
					Thumbnailator.createThumbnail(myIs, myFos, 20, 20) ;
					
					myIs.close() ;
					myFos.flush() ; 
					myFos.close() ;
				}
				
			} catch (IllegalStateException | IOException e) {
				System.out.println("error: " + e.getMessage());
			} 

		}
		
		return "yourSuccess" ;
	}
}
