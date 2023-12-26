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
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		System.out.println("Authentication 구현객체 정보: " + authentication);
		
		HttpSession session = request.getSession(false) ;
		
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		
		RequestCache requestCache = new HttpSessionRequestCache() ;
		SavedRequest savedRequest = requestCache.getRequest(request, response) ;
		
		Set<String> authNameList = 
				AuthorityUtils.authorityListToSet(authentication.getAuthorities()) ;
		
		if(savedRequest == null) {
			if(authNameList.contains("ADMIN")) {
				response.sendRedirect("/mypro00/myboard/list");
			} else {
				response.sendRedirect("/mypro00/");
			}
		} else {
			super.onAuthenticationSuccess(request, response, authentication);			
		}

	}
	
	

}
