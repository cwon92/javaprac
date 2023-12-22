package com.spring5.mypro00.mapper;

import java.util.List;

import com.spring5.mypro00.common.paging.domain.MyBoardPagingDTO;
import com.spring5.mypro00.domain.MyBoardVO;

public interface MyBoardMapper {

	//게시물 목록조회(READ)
	public List<MyBoardVO> selectMyBoardList(MyBoardPagingDTO myboardPaging) ;
	
	//게시물 총수(READ)
	public long selectRowTotal(MyBoardPagingDTO myboardPaging) ;
	
	//새 게시물 등록(CREATE)
	public int insertMyBoard(MyBoardVO myBoard) ;
	
	//특정 게시물 조회: 특정 게시물 하나의 데이터를 가져옴
	public MyBoardVO selectMyBoard(long bno) ;
	
	//특정 게시물 조회: 특정 게시물 하나의 데이터를 가져옴
	public MyBoardVO selectMyBoard2(long bno) ;
	
	//특정 게시물 수정(UPDATE)
	public int updateMyBoard(MyBoardVO myBoard) ;
	
	//특정 게시물 삭제(DELETE)
	public int deleteMyBoard(long bno) ;
	
	//특정 게시물 삭제요청(UPDATE)
	public int updateBdelFlag(long bno) ;
	
	//게시물 조회수 증가(+1씩 증가)
	public int updateBviewCnt(long bno) ;
	
	
	
	
	//public Date selectSysdate() ;
}
