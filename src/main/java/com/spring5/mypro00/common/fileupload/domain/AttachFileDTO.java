package com.spring5.mypro00.common.fileupload.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttachFileDTO {
	
	private String fileName ; 	//원본파일 이름 저장
	private String uploadPath ; //업로드 경로
	private String uuid;		//파일 이름에 추가된 UUID.toString()값
	private String fileType;	//"I": 이미지파일, "F" 이미지가 아닌 파일
	private String repoPath = "C:/myupload";
	
}
