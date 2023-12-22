package com.spring5.mypro00.test02_mapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring5.mypro00.domain.MyMemberVO;
import com.spring5.mypro00.mapper.MyMemberMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration      //폴더 구분자로 \\ 사용 시 오류 발생. /를 사용하세요.
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/mybatis-contextDEV.xml" ,
                       "file:src/main/webapp/WEB-INF/spring/security-context.xml" ,
                       "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
@NoArgsConstructor
public class MyMemberMapperTests {

    //사용자 패스워드 암호화 
    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder pwencoder;
    
    @Setter(onMethod_ = @Autowired)
    private MyMemberMapper myMemberMapper;

    //회원 등록 테스트: 테스트(1)
//    @Test
//    public void testInsertMyMember() {
//    	
//        MyMemberVO member = new MyMemberVO();
//        
//        for(int i = 0; i < 100; i++) {
//
//        	member.setUserPw(pwencoder.encode("pw" + i));
//
//            if(i <= 70) {
//            	member.setUserId("user" + i);
//            	member.setUserName("일반사용자" + i);
//            
//            } else if (i <= 80) {
//            	member.setUserId("member" + i);
//            	member.setUserName("중급사용자" + i);
//            	
//            } else if (i <= 90) {
//                member.setUserId("manager" + i);
//                member.setUserName("운영자" + i);
//                    
//            } else {
//            	member.setUserId("admin" + i);
//            	member.setUserName("관리자" + i);
//            	
//            } 
//            
//            log.info(member);            
//            myMemberMapper.insertMyMember(member);
//        } //for-End
//    }
    
//    //회원 권한 등록 테스트: 테스트(2)
//    @Test
//    public void testInsertMyMemAuthority() {
//		
//        MyAuthorityVO myAuthority = new MyAuthorityVO();
//        
//        for(int i = 0; i < 100; i++) {
//
//            if(i <= 70) {
//                myAuthority.setUserId("user" + i);
//                myAuthority.setAuthority("ROLE_USER");
//                
//            } else if (i <= 80) {
//                myAuthority.setUserId("member" + i);
//                myAuthority.setAuthority("ROLE_MEMBER");
//                
//            } else if (i <= 90) {
//                myAuthority.setUserId("manager" + i);
//                myAuthority.setAuthority("ROLE_MANAGER");
//                
//            } else {
//                myAuthority.setUserId("admin" + i);
//                myAuthority.setAuthority("ADMIN");
//                
//            }
//            log.info(myAuthority);
//            myMemberMapper.insertMyMemAuthority(myAuthority);
//        } //for-End
//        
//        myAuthority.setUserId("admin99");
//        myAuthority.setAuthority("ROLE_MANAGER");
//        myMemberMapper.insertMyMemAuthority(myAuthority);
//        
//        myAuthority.setUserId("admin99");
//        myAuthority.setAuthority("ROLE_MEMBER");
//        myMemberMapper.insertMyMemAuthority(myAuthority);
//        
//        myAuthority.setUserId("admin91");
//        myAuthority.setAuthority("ROLE_MANAGER");
//        myMemberMapper.insertMyMemAuthority(myAuthority);
//    }
//    
    //회원 정보 조회 테스트: 테스트(3) 
//    @Test
//    public void testRead() {
//        MyMemberVO member = myMemberMapper.selectMyMember("admin99");
//        log.info(member);
//        
//        member.getAuthorityList().forEach(authority -> log.info(authority));
//    }

} //class-end

