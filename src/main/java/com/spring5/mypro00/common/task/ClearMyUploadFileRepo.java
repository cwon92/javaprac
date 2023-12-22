package com.spring5.mypro00.common.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring5.mypro00.domain.MyBoardAttachFileVO;
import com.spring5.mypro00.mapper.MyScheduledMapper;

@Component
public class ClearMyUploadFileRepo {
	
//	@Scheduled(cron = "*/5 * * * * *")
//	public void clearNeedlessFiles1() {
//		System.out.println("자동으로 실행되는 스케줄 메서드입니다>>>>>>>>>>>");
//	}
	
	private MyScheduledMapper myScheduledMapper;
	
	public ClearMyUploadFileRepo(MyScheduledMapper myScheduledMapper) {
		this.myScheduledMapper = myScheduledMapper;
	}
	
	//하루 전 날짜 문자열 생성 메서드
	private String getPathStringBeforeOneDay() {
		
		SimpleDateFormat mySdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar myCalendar = Calendar.getInstance();
		
		myCalendar.add(Calendar.DAY_OF_MONTH, 0);
		
		String pathStringBeforeOneDay = mySdf.format(myCalendar.getTime());
		
		
		return pathStringBeforeOneDay;
	}
	
	//스케줄러 메서드
	//반환타입이 void로 설정해야 한다.
	//매개변수를 갖지 않는다
//	@Scheduled(cron = "* * * * * *")
	public void clearneedlessFiles() {
		
		String repoPath = "C:/myupload" ;
		List<MyBoardAttachFileVO> doNotDeleteFileList 
//			= myScheduledMapper.selectAttachFilesuntilBeforeOneDay();
			= myScheduledMapper.selectAttachFilesDuringBeforeOneDay();
		
		//하루 전 날짜경로가 저장된 파일 객체
		File beforeOneDay = Paths.get(repoPath + "/" + getPathStringBeforeOneDay()).toFile();
		
		List<String> dateDirList = new ArrayList<String>();
		String dateDir = null;
		
		for(MyBoardAttachFileVO doNotDeleteFile : doNotDeleteFileList) {
			dateDir = doNotDeleteFile.getUploadPath();
			dateDirList.add(dateDir);
		}
		
		HashSet<String> dateDirSet = new HashSet<String>(dateDirList);
		for(String dateDir2 : dateDirSet) {
			System.out.println("dateDir2: " + dateDir2);
		}
		
		if(doNotDeleteFileList == null) {
			System.out.println("========== 필요한 첨부파일이 없습니다. ============");
			
			//삭제해야되는 필요없는 파일 목록 생성
			File[] needlessUploadFileArray = beforeOneDay.listFiles();
			
			//파일삭제
			if(needlessUploadFileArray == null || needlessUploadFileArray.length == 0) {
				System.out.println("=================== 삭제할 파일이 없습니다.===================");
				return;
			}else {
				System.out.println("=================== 삭제할 파일 목록 ===================");
				for(File needlessUploadFile : needlessUploadFileArray) {
					System.out.println("Deleted FileName: " + needlessUploadFile);
					needlessUploadFile.delete();
				}
			}
			
		}else {	//doNotDeleteFileList != null  //List<MyBoardAttachFileVO>
				//DB로부터 전달된 데이터가 있을 경우

			List<Path> doNotDeleteFilePathList
			= doNotDeleteFileList	//List<MyBoardAttachFileVO> 타입
				.stream()			// ->Stream<MyBoardAttachFileVO> 로 변환
				.map(attachFile -> Paths.get(attachFile.getRepoPath() + "/" +
											 attachFile.getUploadPath() + "/" +
											 attachFile.getUuid() + "_" +
											 attachFile.getFileName()))			//Stream<Path> 로 변환
				.collect(Collectors.toList()); //Stream<Path> --> List<Path>로 변환
			
			doNotDeleteFileList //List<MyBoardAttachFileVO> 타입, thumbnail이 제외된 F/I파일 정보
			.stream()			// ->Stream<MyBoardAttachFileVO> 로 변환
			.filter(attachFile -> attachFile.getFileType().equals("I"))
			.map(attachFile -> Paths.get(attachFile.getRepoPath() + "/" +
										 attachFile.getUploadPath() + "/s_" +
										 attachFile.getUuid() + "_" +
										 attachFile.getFileName()))	
			.forEach(thumbnailPath -> doNotDeleteFilePathList.add(thumbnailPath));  //Stream<Path> 로 변환
			//List<Path> doNotDeleteFilePathList: 일반파일과 썸네일파일 이미지파일의 Path객체들이 저장됨
			
			System.out.println("=================== 지우면 안되는 파일 목록 ===================");
			for(Path doNotDeletePath : doNotDeleteFilePathList) {
				System.out.println("Deleted FileName: " + doNotDeletePath);
			}
			System.out.println("===============");
			
			//삭제해야되는 필요없는 파일 목록 생성
			File[] needlessFileArray 
				= beforeOneDay.listFiles(
						eachFile -> doNotDeleteFilePathList.contains(eachFile.toPath()) == false);
			
			//파일삭제
			if(needlessFileArray == null || needlessFileArray.length == 0) {
				System.out.println("=================== 삭제할 파일이 없습니다.===================");
				return;
			}else {
				System.out.println("=================== 삭제할 파일 목록 ===================");
				for(File needlessFile : needlessFileArray) {
					System.out.println(needlessFile.getAbsolutePath());
					needlessFile.delete();
				}
			}
			
			
		}
		
	}
}
