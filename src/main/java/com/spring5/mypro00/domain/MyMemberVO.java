package com.spring5.mypro00.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyMemberVO {
	private String userId ;
	private String userPw ;
	private String userName ;
	private Timestamp mregDate ;
	private Timestamp mmodDate ;  //암호 등 계정 정보 수정 날짜시간
	private String mdropFlag ;    //'0'(false) - 유지, '1'(true) - 탈퇴요청
	private boolean enabled ;	// 0 (false) - 비활성화 상태, 1 (true) - 활성화 : 휴면계정, 정지상태로 활용
								// 컬럼 데이터유형은 CHAR(1)
	private List<MyAuthorityVO> authorityList ;
	
	private boolean accountNonExpired ;

}


