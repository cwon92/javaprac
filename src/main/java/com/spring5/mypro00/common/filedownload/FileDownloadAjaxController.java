package com.spring5.mypro00.common.filedownload;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileDownloadAjaxController {
	
	@GetMapping(value = "/displayThumbnail")
	public ResponseEntity<byte[]> sendThumbnail(String fileName) {
		
		File thumbnailFile = new File(fileName) ;
		
		ResponseEntity<byte[]> result = null ;
		
		HttpHeaders httpHeaders = new HttpHeaders() ;
		
		try {
			httpHeaders.add("Content-Type", Files.probeContentType(thumbnailFile.toPath())) ;
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(thumbnailFile), 
											    httpHeaders, 
											    HttpStatus.OK) ; ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result ;
	}
	
	@GetMapping(value = "/fileDownloadAjax", 
			    produces = "application/octet-stream")
	@ResponseBody
	public ResponseEntity<Resource> 
				fileDownloadAjaxAction(String fileName
//									   ,@RequestHeader("User-Agent") String userAgent
									   ){

		//C:/myupload/2023/12/15/2a2de188-a0ca-41e7-8674-0dc39100fee7_업로드 테스트압축파일.txt
		//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36
		//Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0
		
		Resource myResource = new FileSystemResource(fileName) ;
		
		if(!myResource.exists()) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND) ;
		}
		
		String downloadName = myResource.getFilename() ;
		//2a2de188-a0ca-41e7-8674-0dc39100fee7_업로드_테스트압축파일.txt
		
		//UUID가 제거된 파일이름
		downloadName = downloadName.substring(downloadName.indexOf("_") + 1) ;
		
		HttpHeaders httpHeaders = new HttpHeaders() ;
		
		String _downloadName = null ;
		
		try {
			//_downloadName = URLEncoder.encode(downloadName, "utf-8") ; //IE, OLD-Edg
			_downloadName = new String(downloadName.getBytes("UTF-8"), "ISO-8859-1"); //[IE, OLD-Edg] 아닌 브라우저

			
		} catch (UnsupportedEncodingException e) {
			e.getMessage() ;
		}
		
		httpHeaders.add("Content-Disposition", "attachment; fileName=" + _downloadName) ;
		
		return new ResponseEntity<Resource>(myResource, httpHeaders, HttpStatus.OK) ;
	}

}
