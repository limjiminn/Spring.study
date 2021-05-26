package com.beer.shop.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beer.shop.member.domain.MemberDTO;

@Controller
@RequestMapping("/member")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	//로그인
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String loginForm(Model model) throws Exception{
		
		logger.info("login form.............");
		
		model.addAttribute("memberDTO",  new MemberDTO());
		
		return "member/login";
	}
	
}
