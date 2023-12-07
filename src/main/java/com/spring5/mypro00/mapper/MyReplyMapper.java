package com.spring5.mypro00.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring5.mypro00.common.paging.domain.MyReplyPagingDTO;
import com.spring5.mypro00.domain.MyReplyVO;

public interface MyReplyMapper {
	//기본 CRUD 처리 메서드 정의

	//특정 게시물에 대한 댓글 목록 조회: 페이징 고려
	public List<MyReplyVO> selectMyReplyList(MyReplyPagingDTO myreplyPaging);
	
	//특정 게시물에 대한 댓글 총 개수
	public long selectRowTotal(long bno);
	
	//특정 게시물에 대한 댓글 등록(prno: null)
	public long insertMyReplyForBoard(MyReplyVO myreply);
	
	//댓글에 대한 답글 등록(prno: 부모글의 rno 값)
	public long insertMyReplyForReply(MyReplyVO myreply);
	
	//특정 게시물에 대한 특정 댓글/답글 조회
	public MyReplyVO selectMyReply(@Param("bno") long bno, @Param("rno") long rno);

	//특정 게시물에 대한 특정 댓글/답글 수정
	public int updateMyReply(MyReplyVO myreply);
	
	//특정 게시물에 대한 특정 댓글/답글 삭제(rdelFlag를 1로 업데이트)
	public int updateRdelFlag(@Param("bno") long bno, @Param("rno") long rno);
	
	//특정 게시물에 대한 모든 댓글 삭제
	public int deleteAllReply(long bno);
	

}
