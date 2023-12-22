package com.spring5.mypro00.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class MyLoginSucessHandler2 extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
										HttpServletResponse response,
										Authentication authentication) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		//로그인 페이지를 거쳤으면 jsp는 httpsession객체가 있음 따라서 로그인페이지를 안거쳤으면 null을 반환시키게 짰음
		
		if(session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);//공부해보자 
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}

	
	
}
