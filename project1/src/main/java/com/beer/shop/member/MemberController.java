package com.beer.shop.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService service;
	
	//로그인
//	@RequestMapping(value = "/login" , method = RequestMethod.GET)
//	public String loginForm(Model model) throws Exception{
//		
//		logger.info("login form.............");
//		
//		model.addAttribute("memberDTO",  new MemberDTO());
//		
//		return "member/login";
//	}
	
	//회원가입 GET (회원가입 폼으로 이동할때)
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void insertGET() throws Exception {
		logger.info("insert GET.......");
	}
	
	//회원가입 POST(회원가입 버튼을 눌렀을때)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String insertPOST(MemberDTO mDto) throws Exception {
		logger.info("insert POST.....");
		
		service.insertMember(mDto);
		
		return null;
	}
	
}
