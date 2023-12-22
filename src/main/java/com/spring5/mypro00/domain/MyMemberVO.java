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
	private String userId;
	private String userPw;
	private String userName;
	private Timestamp mregDate;
	private Timestamp mmodDate;
	private String mdropFlag;
	private boolean enabled;
	
	private List<MyAuthorityVO> authorityList;
}
