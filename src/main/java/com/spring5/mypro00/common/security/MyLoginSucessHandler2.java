package com.spring5.mypro00.common.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class MyLoginSucessHandler2 extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication authentication) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		//로그인 페이지를 거쳤으면 jsp는 httpsession객체가 있음 따라서 로그인페이지를 안거쳤으면 null을 반환시키게 짰음
		
		if(session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);//공부해보자 (로그인실패이력을지우기위해가져옴)
		}
		
		//로그인을 요구한 요청 URL 정보를 가져 가져오는 코드 시작
		//RequestCache에 대하여 https://www.inflearn.com/questions/35556 페이지 설명 참고
		//로그인이 요청된 브라우저의 URL을 메모리에 저장
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);//메모리에 저장된 요청을 가져옴
		
		Set<String> authNameList = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		//권한 이름을 리스트 객체에 담아놓음
		
		if(savedRequest == null) {//사용자가 직접 로그인 페이지를 요청한 경우
			if(authNameList.contains("ADMIN")) {
				response.sendRedirect("/mypro00/myboard/list");
			} else {
				response.sendRedirect("/mypro00/");
			}
		} else {//로그인 페이지가 게시물 등록페이지 등을 눌러서 권한 확인을 요청하는 경우
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

	
	
}
