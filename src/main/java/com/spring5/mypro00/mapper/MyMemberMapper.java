package com.spring5.mypro00.mapper;

import com.spring5.mypro00.domain.MyAuthorityVO;
import com.spring5.mypro00.domain.MyMemberVO;

public interface MyMemberMapper {
	
	//특정 회원 조회: 회원 권한도 함께 조회됨 (스프링 시큐리티도 사용함)
	 public MyMemberVO selectMyMember(String userid);
	 
	 //회원 등록: 회원 등록 시 회원 권한 추가도 같이 수행
	 public Integer insertMyMember(MyMemberVO mymember);
	 
	 //회원 권한 추가
	 public Integer insertMyMemAuthority(MyAuthorityVO myAuthority);
	 
	 public void updateMdropFlag(String userId);

}
