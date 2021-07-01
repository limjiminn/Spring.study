package shop.awesomejump.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.persistence.IMemberDAO;


@Controller
public class MemberControllor {
	
	@Autowired
	private IMemberDAO member;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String join(MemberDTO mDto,HttpServletRequest request, HttpServletResponse response) throws Exception{

		//System.out.println(mDto.toString());
		int result = 0;
		result = member.insertMember(mDto);
		
		if(result>0) {
			System.out.println("회원가입끗");
		}
		return "/awesomejump/register";
	}
}
