package com.spring5.mypro00.service;

import java.util.List;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingCreatorDTO;
import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardVO;

public interface MyBoardService {
	
	//특정 게시물 목록조회(READ)
//	public List<MyBoardVO> getBoardList(MyBoardPagingDTO myboardPaging) ;
	public MyBoardPagingCreatorDTO getBoardList(MyBoardPagingDTO myboardPaging) ;
	
	//새 게시물 등록(CREATE):
	public long registerBoard(MyBoardVO myBoard);
	
	//특정 게시물 조회: 특정 게시물 하나의 데이터를 가져옴(조회수 1증가 고려)
	public MyBoardVO getBoard(long bno);
	
    //특정 게시물 수정 삭제 화면 호출 & 수정 후 조회페이지 호출(조회수 증가 없음)
	public MyBoardVO getBoard2(long bno) ;
	
	//특정 게시물 수정(UPDATE)
	public boolean modifyBoard(MyBoardVO myBoard);
	
	//특정 게시물 삭제(DELETE)
	public boolean removeBoard(long bno);
	
	//특정 게시물 삭제요청
	public boolean modifyBdelFlag(long bno);
	

	
	

}
