package com.spring5.mypro00.common.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//@Controller
public class FileUploadAjaxControllerV2 {
	
	private String uploadFileRepoDIr = "C:/myupload" ;
	
	//form을 통한 다중 파일 업로드  //uploadFiles
	
	//1. 파일 업로드 요청 JSP 페이지 호출
	
	//@GetMapping(value= {"/fileUploadAjax"})
	public String callFileUploadFormPage() {
		System.out.println("'Ajax를 통한 업로드 테스트' JSP 페이지 호출======== ");
		return "sample/fileUploadAjax";
		
	}
	
	//2. 파일 업로드 처리
	//@PostMapping(value = "/fileUploadAjaxAction")
	@ResponseBody
	public String fileUploadActionForm(MultipartFile[] yourUploadFiles) {
		
		String fileName = null ;
		
		//UUID를 이용한 고유한 파일이름 적용
		String myUuid = null ;
		
		for(MultipartFile uploadFile : yourUploadFiles) {
			System.out.println("=============================");
			System.out.println("Upload File Name: " + uploadFile.getOriginalFilename());
			System.out.println("Upload File Size: " + uploadFile.getSize());
			
//			File saveuploadFile = new File(uploadFileRepoDIr, uploadFile.getOriginalFilename());
			
			fileName = uploadFile.getOriginalFilename();
			// 파일이름.확장자, 경로명\파일이름.확장자, 파일이름만 남기는 처리.
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1) ;
			
			//UUID를 이용한 고유한 파일이름 적용
			myUuid = UUID.randomUUID().toString() ;
			
			fileName = myUuid + "_" + fileName ;
			
			File saveuploadFile = new File(uploadFileRepoDIr, fileName);
			
			try {
				uploadFile.transferTo(saveuploadFile);
				
			} catch (IllegalStateException | IOException e) {
				System.out.println("error: " + e.getMessage());
			} 

		}
		
		return "yourSuccess" ;
	}
}
