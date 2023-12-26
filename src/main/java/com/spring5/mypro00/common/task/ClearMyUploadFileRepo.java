package com.spring5.mypro00.common.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
// 스캐쥴러 개요 실습	
//	@Scheduled(cron = "*/5 * * * * *")  //5초마다 실행
//	public void clearNeedlessFiles1() {
//		
//		System.out.println("자동으로 실행되는 스케쥴 메서드11111입니다==========================");
//		
//	}
//	@Scheduled(cron = "*/10 * * * * *")  //10초마다 실행
//	public void clearNeedlessFiles2() {
//		
//		System.out.println("자동으로 실행되는 스케쥴 메서드22222입니다==========================");
//		
//	}
	
	//원하는 자동처리 기능
	//	데이터베이스에 저장된 첨부파일 정보를 이용해서
	//	각 정보에 대응되는 첨부파일을 유지
	//	DB 정보가 없는 첨부파일은 삭제시킴
	
	private MyScheduledMapper myScheduledMapper ;
	
	public ClearMyUploadFileRepo(MyScheduledMapper myScheduledMapper) {
		this.myScheduledMapper = myScheduledMapper ;
	}
	
	//하루 전 날짜 문자열 생성 메서드
	private String getPathStringBeforeOneDay() {
		
		SimpleDateFormat mySdf = new SimpleDateFormat("yyyy/MM/dd") ;
		
		Calendar myCalendar = Calendar.getInstance() ;
		myCalendar.add(Calendar.DAY_OF_MONTH, -1) ; //(오늘 날짜 -1)
		
		String pathStringBeforeOneDay = mySdf.format(myCalendar.getTime());
		System.out.println("pathStringBeforeOneDay: " + pathStringBeforeOneDay);
		
		return pathStringBeforeOneDay ;
//		return pathStringBeforeOneDay.replace("-", File.separator) ;
		
		
	}
	
	//스캐쥴러 메서드: @Scheduled가 명시된 메서드
	//@Scheduled()에 명시된 시간에 자동으로 실행되는 메서드  
	//- 반환타입을 void 로 설정해야 됩니다.
	//- 매개변수를 갖지 않습니다.
	@Scheduled(cron = "0 10 18 * * *") //매일 03시
	public void clearNeedlessFiles() {
		
		String repoPath = "C:/myupload" ;
		
		//하루 전 날짜경로가 저장된 파일 객체
		File beforeOneDay = 
				Paths.get(repoPath + "/" + getPathStringBeforeOneDay()).toFile();
		
		
		List<MyBoardAttachFileVO> doNotDeleteFileList =
		//		myScheduledMapper.selectAttachFilesUntilBeforeOneDay() ;
				myScheduledMapper.selectAttachFilesDuringBeforeOneDay() ;
		
//		List<String> dateDirList = new ArrayList<String>() ;
//		String dateDir = null ;
//		
//		for(MyBoardAttachFileVO doNotDeleteFile : doNotDeleteFileList) {
//			dateDir = doNotDeleteFile.getUploadPath() ;
//			System.out.println("dateDir: " + dateDir);
//			dateDirList.add(dateDir) ;
//		}
//		
//		HashSet<String> dateDirSet = new HashSet<String>(dateDirList) ;
//		for(String dateDir2 : dateDirSet) {
//			System.out.println("dateDir2: " + dateDir2);
//		}
		
		if(doNotDeleteFileList == null) {
			System.out.println("=========== 필요한 첨부파일이 없습니다. ===========");
			
			//삭제해야되는 필요없는 파일 목록 생성
			File[] needlessUploadFileArray = beforeOneDay.listFiles();
			System.out.println("needlessUploadFileArray: \n" + Arrays.toString(needlessUploadFileArray));
			
			//파일 삭제
			if (needlessUploadFileArray == null || needlessUploadFileArray.length == 0) {
				System.out.println("=================== 삭제할 파일이 없습니다.===================");
				return ;
			} else {
				System.out.println("====================== 삭제 파일 목록 ======================");
				for(File needlessUploadFile : needlessUploadFileArray) {
					System.out.println("Deleted FileName: " + needlessUploadFile);
					needlessUploadFile.delete() ;
				}
				System.out.println("========================================================");
			}
			
		} else { //doNotDeleteFileList != null  //List<MyBoardAttachFileVO>
				 //DB로부터 전달된 데이터가 있을 경우
			List<Path> doNotDeleteFilePathList
				= doNotDeleteFileList  //List<MyBoardAttachFileVO> 타입
				  .stream()   // ->Stream<MyBoardAttachFileVO> 로 변환
				  .map(attachFile -> Paths.get(attachFile.getRepoPath() + "/" +
						  					   attachFile.getUploadPath() + "/" +
						  					   attachFile.getUuid() + "_" +
						  					   attachFile.getFileName()
						  					   ))  //Stream<Path> 로 변환
				  .collect(Collectors.toList());  //List<Path>로 변환
			
			doNotDeleteFileList  //List<MyBoardAttachFileVO> 타입, thumbnail이 제외된 F/I 파일 정보
			.stream()   //->Stream<MyBoardAttachFileVO> 로 변환
			.filter(attachFile -> attachFile.getFileType().equals("I")) //이미지만 존재하는 Stream<MyBoardAttachFileVO>
			.map(attachFile -> Paths.get(attachFile.getRepoPath() + "/" +
									     attachFile.getUploadPath() + "/s_" +
									     attachFile.getUuid() + "_" +
									     attachFile.getFileName()
									   ))  //Stream<Path> 로 변환, thumbnail의 Stream<Path>
			.forEach(thumbnailPath -> doNotDeleteFilePathList.add(thumbnailPath)) ;
			
			//List<Path> doNotDeleteFilePathList: 일반파일과 썸네일파일 이미지파일의 Path 객체들이 저장됨
			
			System.out.println("====================== 지우면 않되는 파일 목록 ======================");
			for(Path doNotDeletePath : doNotDeleteFilePathList) {
				System.out.println(doNotDeletePath);
			}
			System.out.println("===============================================================");
			
//beforeOneDay
			
			//삭제해야 되는 필요없는 파일 목록 생성
			File[] needlessFileArray 
				= beforeOneDay.listFiles(
						eachFile -> doNotDeleteFilePathList.contains(eachFile.toPath()) == false) ;
			
			if (needlessFileArray == null || needlessFileArray.length == 0) {
				System.out.println("=================== 삭제할 파일이 없습니다.===================");
				return ;
			
			} else {
				System.out.println("====================== 삭제 파일 목록 ======================");
				for(File needlessFile : needlessFileArray) {
					System.out.println(needlessFile.getAbsolutePath());
					needlessFile.delete() ;
				}
				System.out.println("========================================================");
			}

		}
		
	}

}
