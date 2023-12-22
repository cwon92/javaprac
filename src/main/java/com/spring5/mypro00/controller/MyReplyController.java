package com.spring5.mypro00.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;
import com.spring5.mypro00.service.MyReplyService;

@RestController   //@Controller와는 다르게 @RestController 클래스의 메서드는 JSP 파일을 호출하지 않습니다.
@RequestMapping(value={"/replies", "/myreplies"})
public class MyReplyController {
	
	private MyReplyService myReplyService ;

	public MyReplyController(MyReplyService myReplyService) {
		this.myReplyService = myReplyService;
	}
	
	//게시물에 대한 댓글 목록 조회	GET	/replies/pages/{bno}/{pageNum}
	//브라우저 ajax 코드에서 url을 아래처럼 작성
	//http://localhost:8080/mypro00/replies/229402/page/1
	
	@GetMapping( value="/{bno}/page/{pageNum}" ,
			     produces = {"application/json;charset=utf-8" , 
			    			 "application/xml;charset=utf-8"  }
				) //produces: 브라우저로 보내는 데이터 형식을 설정합니다.
	public ResponseEntity<MyReplyPagingCreatorDTO> 
				showReplyList(	@PathVariable("bno") long bno,
								@PathVariable("pageNum") Integer pageNum ){
							  //이 메서드가 호출된 URL로부터 bno 이름의 변수로 전달되는 값을 bno 매개변수에 저장
		
		MyReplyPagingCreatorDTO myreplyPagingCreator = 
				myReplyService.getReplyList(new MyReplyPagingDTO(bno, pageNum)) ;
		
		ResponseEntity<MyReplyPagingCreatorDTO> myResponseEntity =
				new ResponseEntity<MyReplyPagingCreatorDTO>(myreplyPagingCreator, HttpStatus.OK) ;
		
		return myResponseEntity ;
	}
	
	
//게시물에 대한 댓글 등록(rno 반환) POST 		/replies/{bno}/new
	@PostMapping(value = "/{bno}/new" , 
				 consumes = {"application/json;charset=utf-8"} ,//consumes:브라우저--> 메서드로 전송한 데이터 유형
				 produces = {"text/plain; charset=utf-8"} )		//produces:메서드--> 브라우저로 보내는 데이터 유형
	public ResponseEntity<String> registerReplyForBoard(@PathVariable("bno") long bno ,
														@RequestBody MyReplyVO myreply) {
		Long registeredRno = myReplyService.registerReplyForBoard(myreply);
		String _registeredRno = null ;
		
		if (registeredRno != null) {
			_registeredRno = String.valueOf(registeredRno) ;
			
		} else {
			_registeredRno = String.valueOf(registeredRno) ;
		}
		
		System.out.println("_registeredRno: " + _registeredRno);
		
		return registeredRno != null ? new ResponseEntity<String>(_registeredRno, HttpStatus.OK) 
									 : new ResponseEntity<String>(_registeredRno, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//댓글의 답글 등록(rno 반환) POST 	/replies/{bno}/new
	@PostMapping(value = "/{bno}/{prno}/new" , 
				 consumes = {"application/json;charset=utf-8"} ,//consumes:브라우저--> 메서드로 전송한 데이터 유형
				 produces = {"text/plain; charset=utf-8"} )		//produces:메서드--> 브라우저로 보내는 데이터 유형
	public ResponseEntity<String> registerReplyForReply(@PathVariable("bno") long bno ,
														@PathVariable("prno") long prno,
													    @RequestBody MyReplyVO myreply) {
	Long registeredRno = myReplyService.registerReplyForReply(myreply);
	String _registeredRno = null ;
	if (registeredRno != null) {
		_registeredRno = String.valueOf(registeredRno) ;
	} else {
		_registeredRno = String.valueOf(registeredRno) ;
	}
	
	System.out.println("_registeredRno: " + _registeredRno);
	
	return registeredRno != null ? new ResponseEntity<String>(_registeredRno, HttpStatus.OK) 
								 : new ResponseEntity<String>(_registeredRno, HttpStatus.INTERNAL_SERVER_ERROR);
}
	
//게시물에 대한 특정 댓글 조회 GET 				/replies/{bno}/{rno}
//	@GetMapping(value="/{bno}/{rno}" , 
//				produces = "application/json;charset=utf-8")
//	public ResponseEntity<MyReplyVO> showReply(@PathVariable("bno") Long bno,
//											   @PathVariable("rno") Long rno){
//
//		return new ResponseEntity<MyReplyVO>(myReplyService.getMyReply(bno, rno), HttpStatus.OK) ;
//	}
	
	@GetMapping(value="/{bno}/{rno}" , 
				produces = "application/json;charset=utf-8")
	public MyReplyVO showReply(@PathVariable("bno") Long bno,
							   @PathVariable("rno") Long rno){

		return myReplyService.getMyReply(bno, rno) ;
	}
	

//게시물에 대한 특정 댓글 수정 PUT 또는 PATCH 		/replies/{bno}/{rno}
	//Ajax에서의 요청 URI: /mypro00/replies/229402/102, PUT:PATCH
	@RequestMapping(value="/{bno}/{rno}" , 
					method = {RequestMethod.PUT, RequestMethod.PATCH} ,
					consumes = "application/json;charset=utf-8" ,
					produces = "text/plain;charset=utf-8") 
	public String modifyReply(@PathVariable("bno") Long bno ,
							  @PathVariable("rno") Long rno ,
							  @RequestBody MyReplyVO myreply){
		System.out.println("컨트롤러에 전달된 myreply: " + myreply);
		
		if(myReplyService.modifyMyReply(myreply)) {
			return "modifySuccess" ;
		} else {
			return "modifyFail" ;	
		}
		
		
	}
	
	
	//특정 게시물에 대한 특정 댓글/답글 삭제(rdelFlag를 1로 업데이트)	/replies/{bno}/{rno}
	
	@DeleteMapping(value = "/{bno}/{rno}" , produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> removeReply(@PathVariable("bno") Long bno, 
											  @PathVariable("rno") Long rno) {
		
		return myReplyService.modifyRdelFlag(bno, rno)  
			   ? new ResponseEntity<String>("removeSuccess", HttpStatus.OK)
			   : new ResponseEntity<String>("removeFail", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	
//특정 게시물에 대한 모든 댓글 삭제: 삭제 행수가 반환됨
	@DeleteMapping(value = "/{bno}" , produces = "text/plain;charset=utf-8")
	public ResponseEntity<String> removeAllReply(@PathVariable("bno") Long bno){
		
		int deleteRows = myReplyService.removeAllMyReply(bno) ;
		
		return new ResponseEntity<String>(String.valueOf(deleteRows), HttpStatus.OK) ;
	}

}
