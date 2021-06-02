package com.beer.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//로그인한 사용자에 대해서 postHandle()을 통해 HttpSession에 보관하는 처리
public class loginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN ="login";
	private static final Logger log = LoggerFactory.getLogger(loginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session.getAttribute(LOGIN) != null) {
			log.info("이전 session정보 삭제");
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		
		Object memInfo = modelMap.get("memInfo");
		
		if (memInfo != null) {
			log.info("로그인 성공");
			
			session.setAttribute("LOGIN", memInfo);
			//이전 경로 불러오기
			Object dest = session.getAttribute("dest");
			
			if (dest == null) {
				dest =  "/";
			}
			log.info("loginInterceptor Dest : "+ dest);
			//로그인됬으면 dest의 정보를 받아서 redirect로 보내버린다.
			modelAndView.setViewName("redirect:" + (String)dest);
		}
	}
	
}
