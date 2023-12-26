package com.spring5.mypro00.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardAttachFileVO;
import com.spring5.mypro00.domain.MyBoardVO;
import com.spring5.mypro00.mapper.MyBoardAttachFileMapper;
import com.spring5.mypro00.mapper.MyBoardMapper;

//@Service  //서비스 (구현)클래스는 DAO 또는 mapper 인터페이스의 메서드를 호출합니다.
		  //사용되는 DAO 또는 mapper 인터페이스 타입의 필드가 필요합니다.
public class MyBoardServiceImpl2 implements MyBoardService {
	
	private MyBoardMapper myBoardMapper ;
	private MyBoardAttachFileMapper myBoardAttachFileMapper ;
	
	//방법2: 모든 필드 초기화 생성자
	public MyBoardServiceImpl2(MyBoardMapper myBoardMapper, 
							  MyBoardAttachFileMapper myBoardAttachFileMapper) {
		this.myBoardMapper = myBoardMapper;
		this.myBoardAttachFileMapper = myBoardAttachFileMapper ;
		
		//System.out.println("MyBoardServiceImpl의 모든 필드 초기화생성자입니다.");
		//System.out.println("myBoardMapper: " + myBoardMapper);
	}
	
	//위의 필드에 MyBoardMapper 인터페이스 주입
	//방법1:Setter 이용
//	public MyBoardServiceImpl() {
//		System.out.println("MyBoardServiceImpl의 기본생성자입니다.");
//	}
//	
//	@Autowired
//	public void setMyBoardMapper(MyBoardMapper myBoardMapper) {
//		this.myBoardMapper = myBoardMapper;
//		System.out.println("MyBoardServiceImpl의 MyBoardMapper의 Setter입니다.");	
//	}

	//게시물 목록 조회
//	@Override
//	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myboardPaging) {
//		List<MyBoardVO> myBoardList = myBoardMapper.selectMyBoardList(myboardPaging) ;
//
//		return myBoardList;
//		//return myBoardMapper.selectMyBoardList() ;
//	}

	@Override
	public MyBoardPagingCreatorDTO getBoardList(MyBoardPagingDTO myboardPaging) {
				
//		long rowTotal = myBoardMapper.selectRowTotal(myboardPaging) ;
//		
//		List<MyBoardVO> myboardList = myBoardMapper.selectMyBoardList(myboardPaging) ;
//		
//		MyBoardPagingCreatorDTO pagingCreator =
//				new MyBoardPagingCreatorDTO(rowTotal, myboardPaging, myboardList);
		
//		return pagingCreator;
		//return myBoardMapper.selectMyBoardList() ;
		
		String beginDate = myboardPaging.getBeginDate() ;
		String endDate = myboardPaging.getEndDate() ;
		
		//System.out.println("beginDate: " + beginDate);
		//System.out.println("endDate: " + endDate);
		
		Date _endDate = null ;
		Calendar myCal = null ;
		
		if((beginDate != null && beginDate.length() != 0) 
				&& (endDate != null && endDate.length() != 0)) {
			if(beginDate.equals(endDate)) {
				
				SimpleDateFormat myDateFmt = new SimpleDateFormat("yyyy-MM-dd");
				try {
					_endDate = myDateFmt.parse(endDate);//Parses text from the beginning of the given string to produce a date
					myCal = Calendar.getInstance() ;
					myCal.setTime(_endDate); 			//Sets this Calendar's time with the given Date
					
					myCal.add(Calendar.DAY_OF_MONTH, 1);
					
					endDate = myDateFmt.format(myCal.getTime()) ; //문자열로 변환
					System.out.println("변환 후 endDate: " + endDate);
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				myboardPaging.setEndDate(endDate);
			}
			
		}
		
		return new MyBoardPagingCreatorDTO(myBoardMapper.selectRowTotal(myboardPaging), 
										   myboardPaging, 
										   myBoardMapper.selectMyBoardList(myboardPaging)) ;
	}

	//게시물 등록
	@Override //저장된 게시물의 bno 값을 반환
	@Transactional
	public long registerBoard(MyBoardVO myboard) {
		
		//System.out.println("컨트롤러 ->서비스로 전달된 myBoard: " + myBoard);
		//컨트롤러 ->서비스로 전달된 myBoard: MyBoardVO(bno=0, btitle=서비스 새글 입력  테스트 제목, ....)
		
		//long rows = myBoardMapper.insertMyBoard(myBoard) ; 
		//System.out.println("rows: " + rows); //1
		
		//System.out.println("DB 처리 후myBoard: " + myBoard);
		//DB 처리 후myBoard: MyBoardVO(bno=41, btitle=서비스 새글 입력  테스트 제목, ....)
		
		//return (rows == 1) ? myBoard.getBno() : 0;
		
		myBoardMapper.insertMyBoard(myboard) ;
		
		List<MyBoardAttachFileVO> attachFileList = myboard.getAttachFileList() ;
		
		if (attachFileList != null && attachFileList.size() > 0) {
			
			attachFileList.forEach(
					
					attachFile -> {
						attachFile.setBno(myboard.getBno()) ;
						myBoardAttachFileMapper.insertAttachFile(attachFile) ;
						
			}); //forEach-end
			
		}
		
		return myboard.getBno() ;
	}

	//특정 게시물 조회: 특정 게시물 하나의 데이터를 가져옴
	@Override
	@Transactional
	public MyBoardVO getBoard(long bno, String result) {
		
		MyBoardVO myboard = myBoardMapper.selectMyBoard(bno) ;
		
		if (result == null) {//목록페이지에서 조회요청 시에만
			myBoardMapper.updateBviewCnt(bno) ;
		}
		
		System.out.println("myBoard: " + myboard);
		System.out.println("조회수: " + myboard.getBviewCnt());
		
		return myboard ; 
		
	}
	
	//특정 게시물 수정 삭제 화면 호출 
	@Override
	public MyBoardVO getBoard2(long bno) {
		
		MyBoardVO myboard = myBoardMapper.selectMyBoard2(bno);
		System.out.println("myBoard: " + myboard);

		return myboard ; 
		
	}

	//특정 게시물 수정
	@Override
	@Transactional
	public boolean modifyBoard(MyBoardVO myboard) {
		
		//게시물 수정
		//첨부파일 정보 수정(기존 첨부파일정보 삭제 후, 수정페이지에서 전달된 파일정보를 입력)
		
		long bno = myboard.getBno();

		boolean boardModifyResult = (myBoardMapper.updateMyBoard(myboard) == 1);
		
		myBoardAttachFileMapper.deleteAttachFiles(bno); 
		
		List<MyBoardAttachFileVO> attachFileList = myboard.getAttachFileList() ;
		
		if (boardModifyResult && attachFileList != null) {
			for(MyBoardAttachFileVO attachFile : attachFileList) {
				attachFile.setBno(bno);
				myBoardAttachFileMapper.insertAttachFile(attachFile) ;	
			}
			
		}

		return boardModifyResult;
		

	}

//게시물삭제: 실제삭제(첨부파일 삭제 후, 게시물 삭제)
//실습에서만 사용(댓글이 없어야 함)
	@Override
	public boolean removeBoard(long bno) {
		
		int rows = myBoardMapper.deleteMyBoard(bno);

		return (rows == 1) ;
	}
	
//게시물삭제: 블라인드 처리	
	@Override
	@Transactional
	public boolean modifyBdelFlag(long bno) {
		
		int rows = myBoardMapper.updateBdelFlag(bno);

		return (rows == 1) ;
	}
	
	
	//특정 게시물의 첨부파일 목록 조회
	@Override
	public List<MyBoardAttachFileVO> getAttachFileList(Long bno) {
		return myBoardAttachFileMapper.selectAttachFiles(bno) ;
	}
	
	

}
