package shop.awesomejump.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.persistence.IMemberDAO;

@Controller
public class MemberControllor {

	@Autowired
	private IMemberDAO member;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String join(MemberDTO mDto, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// System.out.println(mDto.toString());
		int result = 0;
		result = member.insertMember(mDto);

		if (result > 0) {
			System.out.println("회원가입끗");
		}
		return "/awesomejump/register";
	}
	
	@RequestMapping("/email_check")
	public String id_check(String userEmail, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String email = request.getParameter("email");
		int message = member.confirmEmail(email);
		
		JSONObject jObj = new JSONObject();
		
		jObj.put("message", message);
		jObj.put("email", userEmail);
		
		response.setContentType("application/x-json; charset=UTF-8"); 
		response.getWriter().print(jObj);
		return null;
	}
	
	@RequestMapping("/nickName_check")
	public String nickName_check(String usernickName, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String nickName = request.getParameter("nickName");
		int message = member.confirmNickname(nickName);
		
		JSONObject jObj = new JSONObject();
		
		jObj.put("message", message);
		jObj.put("nickName", usernickName);
		
		response.setContentType("application/x-json; charset=UTF-8"); 
		response.getWriter().print(jObj);
		return null;
	}

	@Secured("ROLE_USER")
	@GetMapping("/members")
	public String members(@AuthenticationPrincipal MemberDTO member, Model model) {
		
		model.addAttribute(model);
		return "awesomejump/members";
	}

	@PostMapping("/loginAdd")
	public String postloginAdd(String email, String nickName, Model model) throws Exception {
		
		model.addAttribute("email",email);
		model.addAttribute("nickName", nickName);
		
		return "awesomejump/loginAdd";
	}

	
	@GetMapping("/googleLogin")
	public String googleLogin() {
		return "awesomejump/googleLogin";
	}
	
	@RequestMapping("/naver_callback")
	public String naver_callback() {
		
		return "awesomejump/naver_callback";
	}
}
