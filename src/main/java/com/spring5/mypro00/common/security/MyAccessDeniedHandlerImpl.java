package com.spring5.mypro00.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

public class MyAccessDeniedHandlerImpl extends AccessDeniedHandlerImpl{
	
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		System.out.println("accessDeniedException오류 메시지: " + accessDeniedException.getMessage());
		
		Authentication myAuth = SecurityContextHolder.getContext().getAuthentication() ;
		Set<String> authNameList = AuthorityUtils.authorityListToSet(myAuth.getAuthorities()) ;
		
		if(authNameList.contains("ROLE_MANAGER")) {
			response.sendRedirect("/mypro00/myboard/list");
			
		} else {
			response.sendRedirect("/mypro00/");
		}
		
		
		//톰캣이 만든 request, response를 이용한 리다이렉트
		//response.sendRedirect("/mypro00/");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter myPw = response.getWriter() ;
//		
//		String sendHTML = "<script>"
//						+ "   alert('허용되지 않는 접근입니다.'); "
//						+ "   location.href='/mypro00/' ;"
//						+ "</script>" ;
//		
//		myPw.write(sendHTML) ;
//		
//		myPw.flush() ;
//		myPw.close() ;
		
//		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy() ;
//		redirectStrategy.sendRedirect(request, response, "/accessForbiddenError") ;
		//컨텍스트 패스를 포함시키면 않됩니다.
		//super.handle(request, response, accessDeniedException);
	}

}
