package com.spring5.mypro00.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring5.mypro00.domain.MyMemberVO;
import com.spring5.mypro00.mapper.MyMemberMapper;

public class MyMemberUserDetailsServiceImpl implements UserDetailsService {

	//setter 방식 주입이 구성되어야 security-context.xml에 정상적인 설정이 가능
	private MyMemberMapper myMemberMapper ;
	
	@Autowired
	public void setMyMemberMapper(MyMemberMapper myMemberMapper) {
		this.myMemberMapper = myMemberMapper;
	}
	
	//단일 생성자 주입방식은 security-context.xml에 설정 시에 오류발생
	// private MyMemberMapper myMemberMapper;
	// public MyMemberUserDetailsServiceImpl(MyMemberMapper myMemberMapper) {
	//    this.myMemberMapper = myMemberMapper;
	// }
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyMemberVO mymember = myMemberMapper.selectMyMember(username) ;
		
		UserDetails myUserDetail = new MyMemberUser(mymember) ;
		System.out.println("myUserDetail: " + myUserDetail.toString());

		//return mymember == null ? null : new MyMemberUser(mymember) ;
		return mymember == null ? null : myUserDetail ;
	}

	
	
	
	
	
}
