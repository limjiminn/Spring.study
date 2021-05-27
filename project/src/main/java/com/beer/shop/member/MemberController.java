package com.beer.shop.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public void insertGET() throws Exception{
		logger.info("insertGET.........");
	}
	
	//회원가입 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String insertPOST(MemberDTO mDto) throws Exception{
		logger.info("insertPOST.........");
		
		service.insertMember(mDto);
		
		return null;
	}
}
