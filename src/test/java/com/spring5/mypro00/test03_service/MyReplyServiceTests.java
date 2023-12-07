package com.spring5.mypro00.test03_service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring5.mypro00.domain.MyReplyVO;
import com.spring5.mypro00.service.MyReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration    //테스트 시, DispatcherServlet의 servlet-context.xml 설정 구성파일(들)을 사용하기 위한 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({   "file:src/main/webapp/WEB-INF/spring/mybatis-contextDEV.xml",
                          "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class MyReplyServiceTests {
    @Setter(onMethod_ = { @Autowired })
    private MyReplyService myReplyService;
      
    //MyReplyService 빈 생성 유무 확인 테스트, 테스트 후, 메서드만 주석 처리
//    @Test  
//    public void testMyReplyServiceExist() {
//
//        log.info(myReplyService);
//        assertNotNull(myReplyService);
//    }
//
    //특정 게시물의 댓글 목록 조회 테스트 
//    @Test
//    public void testGetReplyList() {
//        MyReplyPagingCreatorDTO myReplyPagingCreator = 
//                myReplyService.getReplyList(new MyReplyPagingDTO(229376L, 2));
//        log.info("댓글-서비스-게시물에 대한 댓글 목록 조회 테스트- 반환된 MyReplyPagingCreatorDTO: " 
//                 + myReplyPagingCreator );
//    }
    
    //게시물에 대한 댓글 등록(rno 반환) 테스트
//    @Test
//    public void testRegisterReplyForBoard() {
//    	MyReplyVO myReply = new MyReplyVO();
//        myReply.setBno(229404L);
//        myReply.setRcontent("서비스 게시물에 대한 댓글 등록 테스트");
//        myReply.setRwriter("test");
//
//        Long registeredRno = myReplyService.registerReplyForBoard(myReply);
//        log.info("서비스 게시물에 대한 댓글 등록 테스트-등록된 댓글번호: " + registeredRno);
//    }

//    //게시물의 댓글에 대한 답글 등록(rno 반환) 테스트
//    @Test
//    public void testRegisterReplyForReply() {
//    	MyReplyVO myReply = new MyReplyVO();
////    	MyReplyVO myReply = new MyReplyVO();
//        myReply.setBno(229401);
//        myReply.setRcontent("서비스 테스트 - 게시물의 댓글에 대한 답글 등록 테스트");
//        myReply.setRwriter("test");
//        myReply.setPrno(41L);  //앞에서 등록한 댓글번호
//
//        Long registeredRno = myReplyService.registerReplyForReply(myReply);
//        log.info("서비스 게시물의 댓글에 대한 답글 등록 테스트-등록된 댓글번호: " + registeredRno);
//    }
////
////    //특정 게시물에 대한 특정 댓글 조회
//    @Test
//    public void testGetReply() {
//        log.info(myReplyService.getReply(229376L, 42L));
//    }
    
    //게시물에 대한 특정 댓글 수정 테스트
//    @Test
//    public void testModifyReply() {
//
//        MyReplyVO myReply = myReplyService.getReply(229376L, 42L);
//
//        if (myReply == null) {
//            return;
//        }
//
//        myReply.setRcontent("서비스-댓글 수정테스트입니다.수정");
//        log.info("서비스-댓글 수정테스트 시 반환된 값(수정된 댓글 수): " + myReplyService.modifyReply(myReply));
//        log.info("서비스-댓글 수정테스트: 수정 후 조회(수정된 myReplyVO): " + myReplyService.getReply(229376L, 42L));
//    }
////    
////    //게시물에 대한 특정 댓글 삭제
////    @Test
////    public void testRemoveReply() {
////        log.info("서비스: 특정 게시물 삭제 테스트: " + myReplyService.removeReply(229376L, 42L));
////    }
//
//
//    
}
