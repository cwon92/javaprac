package com.spring5.mypro00.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring5.mypro00.domain.MyMemberVO;
import com.spring5.mypro00.mapper.MyMemberMapper;

public class MyMemberUserDetailsServiceImpl implements UserDetailsService{
	
	private MyMemberMapper myMemberMapper;
	
	@Autowired
	public void setMyMemberMapper(MyMemberMapper myMemberMapper) {
		this.myMemberMapper = myMemberMapper;
	}
	
	//생성자 주입방식은 security-context.xml에 설정시에 오류발생***********************
	//private MyMemberMapper myMemberMapper;
	//public MyMemberDetailsService(MyMemberMapper myMemberMapper) {
	// this.myMemberMapper = myMemberMapper;
	//}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyMemberVO mymember = myMemberMapper.selectMyMember(username);
		
		return mymember == null ? null : new MyMemberUser(mymember) ;
	}

}
