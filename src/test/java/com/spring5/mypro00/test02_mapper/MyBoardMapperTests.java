package com.spring5.mypro00.test02_mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring5.mypro00.domain.MyBoardVO;
import com.spring5.mypro00.mapper.MyBoardMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/mybatis-contextDEV.xml")
@Log4j
public class MyBoardMapperTests {
	
//    @Setter(onMethod_ = @Autowired)
//    private MyBoardMapper myBoardMapper;

	private MyBoardMapper myBoardMapper ;
	
	@Autowired
	public void setmyBoardMapper(MyBoardMapper myBoardMapper) {
		this.myBoardMapper = myBoardMapper;
	}


//  //게시물 목록 조회 테스트 <-- 테스트 후 메서드 주석처리
//    @Test
//    public void testSelectBoardList() {
//    	myBoardMapper.selectMyBoardList().forEach(myBoard -> log.info(myBoard));
//    }    
    
//    @Test
//    public void testSelectBoardListWithPaging() {
//
//        MyBoardPagingDTO myBoardPagingDTO = new MyBoardPagingDTO();//기본 생성자 이용(1, 10)
//        myBoardMapper.selectMyBoardList(myBoardPagingDTO)
//                     .forEach(myBoard -> System.out.println(myBoard));
//
//        myBoardPagingDTO = new MyBoardPagingDTO(2, 10);
//        myBoardMapper.selectMyBoardList(myBoardPagingDTO)
//                     .forEach(myBoard -> System.out.println(myBoard));
//    }
    

  
//    @Test
//    public void testSearchBoardWithPaging() {
//        MyBoardPagingDTO myBoardPagingDTO = new MyBoardPagingDTO(); //기본 생성자 이용(1, 10)
//        myBoardPagingDTO.setKeyword("5");
//
//        //myBoardPagingDTO.setScope("T");
//        //myBoardPagingDTO.setScope("C");
//        //myBoardPagingDTO.setScope("W");
//        myBoardPagingDTO.setScope("TC");
//        //myBoardPagingDTO.setScope("TCW");
//        //myBoardPagingDTO.setScope("TW");
//        //myBoardPagingDTO.setScope("CW");
//        log.info("행 총 개수: " + myBoardMapper.selectRowTotal(myBoardPagingDTO));
//        
//        List<MyBoardVO> list = myBoardMapper.selectMyBoardList(myBoardPagingDTO);
//        list.forEach(board -> log.info(board));
//    }



  
  
	
//  //게시물 등록 테스트 - selectKey 사용 안함
//  @Test
//  public void testInsertMyBoard() {
//	  
//      MyBoardVO myBoard = new MyBoardVO();
//      myBoard.setBtitle("메퍼 테스트-입력제목");
//      myBoard.setBcontent("매퍼 테스트-입력내용");
//      myBoard.setBwriter("test");
//      
//      myBoardMapper.insertMyBoard(myBoard);
//      myBoardMapper.selectMyBoardList().forEach(_myBoard -> log.info(_myBoard));
//  }
  
  //게시물 등록 테스트 - selectKey 사용
//  @Test
//  public void testInsertMyBoardSelectKey() {
//
//      MyBoardVO myBoard = new MyBoardVO();
//      myBoard.setBtitle("메퍼 테스트-select key");
//      myBoard.setBcontent("매퍼 테스트-select key");
//      myBoard.setBwriter("test");
//      System.out.println("Mapper처리 전 VO: " + myBoard);
//      myBoardMapper.insertMyBoard(myBoard);
//      System.out.println("Mapper처리 후 VO: " + myBoard);
//  }


//  //게시물 조회 테스트(by bno)
  @Test
  public void testSelectMyBoard() {
      // 존재하는 게시물 번호로 테스트
      log.info(myBoardMapper.selectMyBoard(5L));
  }

//  //게시물 삭제 요청 테스트 - bdelFlag 컬럼값이 0 -> 1 로 수정만 됨
//  @Test
//  public void testUpdateBdelFlag() {
//      myBoardMapper.updateSetDelFlag(1L);
//      log.info(myBoardMapper.selectMyBoard(1L));
//  }
//
//  //게시물 삭제 테스트 - 실제 특정 게시물이 삭제됨(18)
//  @Test
//  public void testDeleteMyBoard() {
//      log.info("DELETE COUNT: " + myBoardMapper.deleteMyBoard(18L));
      //log.info(myBoardMapper.selectMyBoard(42L));
  }

//  //게시물 삭제 테스트 - 삭제 요청된 게시물들 전체 삭제 (관리자)
//  @Test
//  public void testDeleteAllBoard() {
//      log.info("DELETE COUNT: " + myBoardMapper.deleteAllBoardSetDeleted());
//      log.info(myBoardMapper.selectMyBoard(10L));
//  }

//  //게시물 수정 테스트
//	@Test
//	public void testUpdateMyBoard() {
//      MyBoardVO myBoard = new MyBoardVO();
//      myBoard.setBno(1L);  //실행 전 존재하는 번호인지 확인할 것
//      myBoard.setBtitle("수정된 제목");
//      myBoard.setBcontent("수정된 내용");
//      
//      log.info("UPDATE COUNT: " + myBoardMapper.updateMyBoard(myBoard));
//      //myBoard = myBoardMapper.selectMyBoard(1L);
//      System.out.println("수정된 행 값: " + myBoard.toString());
//  }

//  //게시물 조회수 증가 테스트: 3번 수행
//  @Test
//  public void testUpdateBviewsCnt() {
//      myBoardMapper.updateBviewsCnt(1L);
//      System.out.println("수정된 행 값: " + myBoardMapper.selectMyBoard(1L).toString());
//  }
    
//  @Test
//  public void testSelectSysdate() {
//  	
//  	log.info(myBoardMapper.selectSysdate());
//  }
  

