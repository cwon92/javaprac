package com.spring5.mypro00.common.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.spring5.mypro00.domain.MyMemberVO;

public class MyMemberUser extends User{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private MyMemberVO mymember ;
	
	public MyMemberUser(MyMemberVO mymember) {
		super(mymember.getUserId(), 
			  mymember.getUserPw(),
			  mymember.getAuthorityList()	//List<MyAuthorityVO>
			  		  .stream()				//Stream<MyAuthorityVO>으로 변환
			  		  .map(myauth -> new SimpleGrantedAuthority(myauth.getAuthority()))	//Stream
			  		  .collect(Collectors.toList())//List<GrantedAuthority>
				);
		
		this.mymember = mymember;
		
	}
	
}
