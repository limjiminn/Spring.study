package com.springstudy.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//특정 경로에 사용자가 접근하는 경우 현재 사용자가 로그인한 상태인지를 체크하는 처리
public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	//자동페이지 이동 : 로그인이 요구되어 로그인 페이지로 접근한 사용자가 로그인한 이후에 원래 경로로 이동시켜주는 동작
	//로그인 페이지 이동전, 현재 페이지를 Session에 저장
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		uri = uri.substring(contextPath.length());
		log.info("uri ===>" + uri);
		
		String query = request.getQueryString();
		
		//기존 URI에 parameter가 있을경우, 이를 포함
		if (query == null || query.equals("null")) {
			query ="";
		}else {
			query ="?" +query;
		}
		
		if (request.getMethod().equals("GET")) {
			log.info("dest : " + (uri + query));
			
			request.getSession().setAttribute("dest", uri + query);
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		
		if (session.getAttribute("login") == null) {
			log.info("current user is not logined");
			
			saveDest(request);
			//로그인하지 않은 사용자일 경우 로그인 페이지로 이동
			response.sendRedirect(contextPath + "/member/login");
			
			return false;
		}
		//로그인한 사용자일 경우 Controller 호출
		return true;
	}
}
