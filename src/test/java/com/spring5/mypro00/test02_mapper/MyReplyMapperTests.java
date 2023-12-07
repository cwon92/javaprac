package com.spring5.mypro00.test02_mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring5.mypro00.domain.MyReplyVO;
import com.spring5.mypro00.mapper.MyReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/mybatis-contextDEV.xml")
@Log4j
public class MyReplyMapperTests {
	
    @Setter(onMethod_ = @Autowired)
    private MyReplyMapper myReplyMapper;
    
    //매퍼 인스턴스 생성 테스트
//    @Test
//    public void testMapper() {
//        log.info(myReplyMapper);
//    }
    
//    @Test
//    public void testSelectMyReplyList() {
//    	
//        long targetBno = 229423L; //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정
//        
//        MyReplyPegingDTO myreplyPeging = new MyReplyPegingDTO(targetBno, 1, 5);
//        myReplyMapper.selectMyReplyList(myreplyPeging)
//        			 .forEach(myReply -> System.out.println(myReply));
//    }

//    //특정 게시물에 대한 댓글 등록 테스트
//    @Test
//    public void testInsertMyReplyForBoard() {
//        
//        MyReplyVO myReply = new MyReplyVO();
//        
//        myReply.setBno(229422L); //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정
//        myReply.setRcontent("매퍼 댓글 입력 테스트 ");
//        myReply.setRwriter("user11");
//        log.info("매퍼 - 추가 전 댓글 객체: " + myReply);
//        
//        myReplyMapper.insertMyReplyForBoard(myReply);
//        log.info("매퍼 - 추가 후 댓글 객체: " + myReply);
//        //myReplyMapper.selectMyReply(229381L, myReply.getRno());
//    }

    
//    //댓글의 답글 등록 테스트
//    @Test
//    public void testInsertMyReplyForReply() {
//    	
//    	
//    	MyReplyVO myReply = new MyReplyVO();
//        myReply.setBno(229381L); //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정
//        myReply.setRcontent("매퍼 댓글의 답글 입력 테스트 ");
//        myReply.setRwriter("user22");
//        myReply.setPrno(1L);
//        log.info("매퍼 - 추가 전 답글 객체: " + myReply);
//        
//        myReplyMapper.insertMyReplyForReply(myReply);
//        log.info("매퍼 - 추가 후 답글 객체: " + myReply);
//        
////      myReplyMapper.selectMyReply(229381L, myReply.getRno());
//    }
//
//   //특정 댓글/답글 조회 테스트
//    @Test
//    public void testSelectMyReply() {
//    	
//        Long targetBno = 229423L; //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정.
//        Long targetRno = 1L;
//        
//        //229423L
//        MyReplyVO myReply = myReplyMapper.selectMyReply(targetBno, targetRno);
//        log.info("특정 댓글/답글 조회 테스트: " + myReply);
//    }

    
//    //특정 댓글 수정 테스트
//    @Test
//    public void testUpdateMyReply() {
//    	
//        Long targetBno = 229423L; //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정.
//        Long targetRno = 1L;
//        
//        MyReplyVO myReply = myReplyMapper.selectMyReply(targetBno, targetRno);
//        myReply.setRcontent("매퍼 - 수정 테스트..");
//        
//        int count = myReplyMapper.updateMyReply(myReply);
//        log.info("UPDATE COUNT: " + count);
//        log.info(myReplyMapper.selectMyReply(targetBno, targetRno));
//    }
//
      //특정 게시물 삭제 요청
//	  @Test
//	  public void testUpdateMyReply() {
//	  	
//	      Long targetBno = 229423L; //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정.
//	      Long targetRno = 12L;
//	      
//	      MyReplyVO myReply = myReplyMapper.selectMyReply(targetBno, targetRno);
//	      myReply.setRcontent("매퍼 - 수정 테스트..");
//	      
//	      int count = myReplyMapper.updateRdelFlag(targetBno, targetRno);
//	      log.info("DELETED COUNT: " + count);
//	      log.info(myReplyMapper.selectMyReply(targetBno, targetRno));
//	  }
    
    
//    //특정 게시물에 대한 특정 댓글 삭제 테스트
//    @Test
//    public void testDeleteMyReply() {
//    	
//        Long targetBno = 229381L; //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정.
//        Long targetRno = 4L;
//        int count = myReplyMapper.deleteMyReply(targetBno, targetRno);
//        log.info("DELETE COUNT: " + count);
//        log.info(myReplyMapper.selectMyReply(targetBno, targetRno));
//    }
    
//     //특정 게시물에 모든 댓글답글 삭제
//	  @Test
//	  public void testDeleteAllReply() {
//	  	
//	      Long targetBno = 229422L; //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정.
//	
//	      int count = myReplyMapper.deleteAllReply(targetBno);
//	      log.info("DELETE COUNT: " + count);
//
//  }    
  

  //특정 게시물에 대한 댓글 목록 조회(페이징) 테스트
//    @Test
//    public void testSelectMyReplyListPaging() {
//        long targetBno = 229381L; //SQL Developer에서 확인된 가장 최신 게시물의 bno를 지정
//
//        MyReplyPagingDTO myReplyPagingDTO = new MyReplyPagingDTO(targetBno, 2);
//
//        long totalReplyCnt = myReplyMapper.selectReplyTotalCnt(myReplyPagingDTO);
//        log.info("댓글 총 개수: "+ totalReplyCnt);
//
//        List<MyReplyVO> myReplies = myReplyMapper.selectMyReplyList(myReplyPagingDTO);
//        myReplies.forEach(myReply -> System.out.println(myReply));
//    }

  
}
