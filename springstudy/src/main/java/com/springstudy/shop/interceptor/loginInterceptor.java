package com.springstudy.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//로그인한 사용자에 대해서 postHandle()을 통해 HttpSession에 보관하는 처리
public class loginInterceptor extends HandlerInterceptorAdapter{
	//LOGIN 대문자? 상수라서 구분할수있게 (명명룰)
	private static final String LOGIN = "login";
	private static final Logger log = LoggerFactory.getLogger(loginInterceptor.class);
	
	//프리핸들러: 지정된 컨트롤러의 동작 이전에 수행할 동작(사전제어)
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if (session.getAttribute(LOGIN) != null) {
			log.info("clear login data before");
			//기존 HttpSession에 남아있는 정보가 있는 경우 이를 삭제
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
	//포스트핸들러: 지정된 컨트롤러의 동작 이후에 처리할 동작(사후제어)
	@Override //재정의
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		//모델앤 뷰에서 쓰임
		ModelMap modelMap = modelAndView.getModelMap();
		
		//멤버컨트롤러에서의 memInfo
		Object memInfo = modelMap.get("memInfo");
		
		if (memInfo != null) {
			log.info("new login success");
			//로그인 성공시  Session에 저장후, 초기화면 이동
			session.setAttribute(LOGIN, memInfo);
			
			//이전 destination 불러오기
			Object dest = session.getAttribute("dest");
//			response.sendRedirect("/");
			
			if (dest == null) {
				dest = "/board/list";
			}
			
			log.info("LoginInterceptor Dest : " + dest);
			//로그인됬으면 dest의 정보를 받아서 redirect로 보내버린다
			modelAndView.setViewName("redirect:" + (String)dest);
		}
	}
	
}
