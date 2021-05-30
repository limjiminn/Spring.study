package com.beer.shop.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	//회원가입 GET
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String insertGET() throws Exception{
		logger.info("insertGET.........");
		
		return "/member/register";
	}
	
	//회원가입 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String insertPOST(MemberDTO mDto) throws Exception{
		logger.info("insertPOST.........");
		
		int count = service.selMember(mDto.getUserid());
		
		try {
			if(count == 0) {
				service.insertMember(mDto);				
			}
		} catch (Exception e) {
			logger.info("존재하는 아아디");
		}
		
		return "redirect:/member/login";
	}
	
	//로그인 화면
	@RequestMapping("/login")
	public String login() {
		logger.info("loginGET........");
		
	return "member/login";
	}
	
	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberDTO mDto, HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		logger.info("post login...........");
		
		HttpSession session = req.getSession();
		
		MemberDTO result = service.selLoginInfo(mDto);
		logger.info("결과 : " + result);
		session.setAttribute("member", result);
//		if (result == null) {
////			session.setAttribute("member", null);
//			rttr.addFlashAttribute("msg", "false");
//			
//		}else {
//			session.setAttribute("member", result);
//		}
		logger.info("결과야 있니?" + session.toString());
		return "redirect:/";
	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		
		return "redirect/";
	}
}
