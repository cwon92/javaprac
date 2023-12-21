package com.spring5.mypro00.service;

import java.util.List;



import com.spring5.mypro00.domain.MyBoardAttachFileVO;

public interface MyScheduledMapper {

	//하루 전 날짜까지 업로드 된 파일 정보 조회
	public List<MyBoardAttachFileVO> selectAttachFilesUntilBeforeOneDay();
	
	//하루 전 날짜동안 업로드 된 파일 정보 조회
	public List<MyBoardAttachFileVO> selectAttachFilesDuringBeforeOneDay();
}
