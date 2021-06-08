package com.beer.shop.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value = "/idchk", method = RequestMethod.POST)
	@ResponseBody
	public  int idchk(@RequestParam("userid") String userid) throws Exception{
		logger.info("아이디 체크 : " + userid);
		
		int count = service.idchk(userid);
		
		return count;
	}
	
	//회원가입 POST
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String insertPOST(MemberDTO mDto) throws Exception{
		logger.info("insertPOST.........");
		int count = service.idchk(mDto.getUserid());
		
		try {
			if(count == 0) {
				logger.info("성공");
				service.insertMember(mDto);				
			}else if(count == 1) {				
				logger.info("실패");
				return "/member/register";
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
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public String login(MemberDTO mDto, HttpSession session, Model model) throws Exception{
		logger.info("post login...........");
		
		String returnURL = "";
		
		MemberDTO memInfo = service.selLoginInfo(mDto);
		logger.info("결과 : " + memInfo);
		
		if (memInfo == null) {
			logger.info("아이디혹은 비밀번호가 잘못되엇습니다.");
			return "redirect:/member/login";
		}
		model.addAttribute("memInfo", memInfo);
		session.setAttribute("member", memInfo);
		session.setAttribute("userid", memInfo.getUserid());
		logger.info("결과야 있니?" + model.toString());
		return returnURL;
	}
	
	//로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	//마이페이지
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage() throws Exception{
		
		logger.info("마이페이지");
		
		return "member/mypage";
	}
	
	//회원정보 수정
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) throws Exception{
		//세션 객체 안에있는 ID정보 저장
//		String userid = (String) session.getAttribute("userid");
//		logger.info("회원 정보수정GET 아이디 " + userid);
//		//서비스안의 회원정보보기 메서드 호출
//		MemberDTO mDto = service.selmember(userid);
//		//정보 저장 후 페이지 이동
//		model.addAttribute("member", mDto);
		
		return "member/update";
		
	}
	//회원정보 수정
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String update(MemberDTO mDto, HttpSession session)throws Exception{
		logger.info("회원 정보수정POST");
		
		service.update(mDto);
		
		
		return "redirect:/";
		
	}
	//회원 탈퇴
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete() throws Exception{	
		return "member/delete";
	}

	//회원 탈퇴
	 @RequestMapping(value = "/delete", method = RequestMethod.POST) 
	 public String delete(MemberDTO mDto, HttpSession session, RedirectAttributes rttr) throws Exception{
		 //세션에 있는 member를 가져와 member변수에 넣는다.
		 MemberDTO member = (MemberDTO) session.getAttribute("member");
		 //세션에있는 비밀번호
		 String sessionpw = member.getUserpw();
		 //DTO로 들어오는 비밀번호
		 String pwDto = mDto.getUserpw();
		 
		 if (!(sessionpw.equals(pwDto))) {
//			rttr.addFlashAttribute("msg", false);
			 logger.info("탈퇴 실패");
			return "redirect:/member/delete";
		}
		 logger.info("탈퇴 성공");
		 service.delete(mDto);
		 session.invalidate();
		 return "redirect:/"; 
	}
	
}
