package com.beer.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);
	
	private void saveDest(HttpServletRequest request) {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		uri = uri.substring(contextPath.length());
		log.info("uri =============>" + uri);
		
		String query = request.getQueryString();
		
		if (query == null || query.equals("null")) {
			query ="";
		}else {
			query ="?" + query;
		}
		if (request.getMethod().equals("GET")) {
			log.info("dest : " + (uri + query));
			
			request.getSession().setAttribute("dest", uri + query);
		}
	}
	//컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//session 객체를 가져옴
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();
		
		//login처리를 담장하는 사용자 정보를 담고 있는 객체를 가져옴
		Object obj = session.getAttribute("member");
		if (obj == null) {
			//로그인이 안되어 있는 상태일때 로그인폼으로 보낸다.
			log.info("로그인이 안되어있네요.");
			
			saveDest(request);
			//로그인폼으로 이동
			response.sendRedirect(contextPath + "/member/login");
			//더이상 컨트롤러 요청으로 가지 않도록 false 반환
			return false;
			
		}
		//컨트롤러로 갈수있게 허용
		return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
