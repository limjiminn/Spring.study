package shop.awesomejump.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.awesomejump.domain.CouponMemDTO;
import shop.awesomejump.domain.Criteria;
import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.domain.PageDTO;
import shop.awesomejump.domain.PointListDTO;
import shop.awesomejump.domain.ProImageDTO;
import shop.awesomejump.domain.ProInterestDTO;
import shop.awesomejump.domain.ProductDTO;
import shop.awesomejump.service.IMemberDAO;
import shop.awesomejump.service.IProductDAO;
import shop.awesomejump.util.FileUtils;

@Controller
public class MemberControllor {

	@Autowired
	private IMemberDAO memberDao;

	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private IProductDAO productService;

//	@RequestMapping("/registerResult")
//	public String test() {
//		return "/awesomejump/registerResult";
//	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String join(MemberDTO mDto, HttpServletRequest request, HttpServletResponse response) throws Exception {

		mDto.setMem_pw(pwdEncoder.encode(mDto.getMem_pw()));
		mDto.setMem_register_path("DEFAULT");
		mDto.setMem_email_yn("N");
		
		int result = 0;
		result = memberDao.insertMember(mDto);
		
		if (result > 0) {
			System.out.println("??????????????????");
			
			// ???????????? ?????? ??????
			MemberDTO couponDto = new MemberDTO();
			couponDto = memberDao.getMemberByEmail(mDto.getMem_email());
			System.out.println("couponDto = >  " + couponDto);
			memberDao.registerCoupon(couponDto);
			
			Random rnd = new Random();
			int code = rnd.nextInt(99999)+10000;
			String setfrom = "ksoyoun95@naver.com";
			String tomail = mDto.getMem_email(); // ?????? ?????? ?????????
			String title = "awesomejump ???????????? ?????? ????????? ?????????"; // ??????
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message,
						true, "UTF-8");

				messageHelper.setFrom(setfrom); // ??????????????? ???????????? ??????????????? ??????
				messageHelper.setTo(tomail); // ???????????? ?????????
				messageHelper.setSubject(title); // ??????????????? ????????? ????????????
				messageHelper.setText(
						"<html>"
						+ "<body><div style='text-align: center;'>"
						+ "<img src='https://ifh.cc/g/FZqwaM.png'>"
						+ "<h1>awesomeJump ???????????? ?????? ?????? ?????????.</h1>"
						+ "<h3>?????? ??????????????? ??????????????? ???????????????</h3>"
						+ "<span><a href='http://sixsenseproject.shop/emailAuth?userEmail="
						//+ "<span><a href='http://localhost:9090/emailAuth?userEmail="
						+ mDto.getMem_email()
						+ "&emailCode="
						+ code
						+ "' style='font-size:20px;'>???????????????</a></span>"
						+ "</div></body>"
						+ "</html>"
						,true); // ?????? ??????

				mailSender.send(message);
				
				mDto.setMem_email_yn(String.valueOf(code));
				memberDao.updateCode(mDto);
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		return "/awesomejump/registerResult";
	}
	
	@RequestMapping("/emailAuth")
	public String emailAuth(@RequestParam("userEmail") String userEmail, @RequestParam("emailCode") String code, Model model) throws Exception {
		
		MemberDTO mDto = new MemberDTO();
		mDto = memberDao.selectMember(userEmail);
		System.out.println("????????? ????????? mDto" + mDto.toString());
		
		if(mDto.getMem_email_yn().equals(code)) {
			mDto.setMem_email_yn("Y");
			memberDao.updateCode(mDto);
			
			return "/awesomejump/emailAuthSuccess";
		}else if(mDto.getMem_email_yn().equals("Y")) {
			
			model.addAttribute("message","?????? ????????? ???????????????.");
			return "/awesomejump/emailAuthFail";
			
		}else {
			
			model.addAttribute("message","????????? ????????? ??????????????????");
			return "/awesomejump/emailAuthFail";
		}
	}
	
	@RequestMapping("/email_check")
	public String id_check(String userEmail, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String email = request.getParameter("email");
		int message = memberDao.confirmEmail(email);
		
		Map<String, Object> jObjMap = new HashMap<>();
		
		jObjMap.put("message", message);
		jObjMap.put("email", userEmail);
		
		JSONObject jObj = new JSONObject(jObjMap);
		
		response.setContentType("application/x-json; charset=UTF-8"); 
		response.getWriter().print(jObj);
		return null;
	}
	
	@PostMapping("/nickName_check")
	public String nickName_check(String usernickName, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String nickName = request.getParameter("nickName");
		int message = memberDao.confirmNickname(nickName);
		
		Map<String, Object> jObjMap = new HashMap<>();
		
		jObjMap.put("message", message);
		jObjMap.put("nickName", usernickName);
		
		JSONObject jObj = new JSONObject(jObjMap);
		
		response.setContentType("application/x-json; charset=UTF-8"); 
		response.getWriter().print(jObj);
		return null;
	}

	@PostMapping("/loginAdd")
	public String postloginAdd(String email, String nickName, String social, String token,Model model) throws Exception {
		
		int checkEmail = memberDao.confirmEmail(email);
		int checkNickName = memberDao.confirmNickname(nickName);
		System.out.println(token+"?????????");
		
		//????????????o
		if(checkEmail == 1) {
			
			MemberDTO member = memberDao.getMemberByEmail(email);
			
			if (member != null) {
				memberDao.updateLastLogin(member);
				MemberDTO ProfileMember = new FileUtils().setMemberProfileImagePath(member);
				SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken
					(ProfileMember, "[Protected]", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
			}
			
			return "redirect:/";
			
		//????????????x ??????????????? o
		}else if(checkEmail == -1 && checkNickName == 1) {
			model.addAttribute("email",email);
			model.addAttribute("nickName", nickName);
			model.addAttribute("token",token);
			model.addAttribute("social",social);
			
			return "awesomejump/loginAdd";
			
		//???????????? x ??????????????? x
		}else {
			MemberDTO mDto = new MemberDTO();
			mDto.setMem_email(email);
			mDto.setMem_nickname(nickName);
			mDto.setMem_pw(token);
			mDto.setMem_register_path(social);
			mDto.setMem_email_yn("Y");
			
			int result = 0;
			result = memberDao.insertMember(mDto);
			
			if (result > 0) {
				System.out.println("??????????????????");
				
				// ???????????? ?????? ??????
				MemberDTO couponDto = new MemberDTO();
				couponDto = memberDao.getMemberByEmail(mDto.getMem_email());
				System.out.println("couponDto = >  " + couponDto);
				memberDao.registerCoupon(couponDto);
				
				MemberDTO memberByEmail = memberDao.getMemberByEmail(email);
				MemberDTO ProfileMember = new FileUtils().setMemberProfileImagePath(memberByEmail);
				
				SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken
				(ProfileMember, "[Protected]", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
			}
			
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/googleLogin")
	public String googleLogin() {
		return "awesomejump/googleLogin";
	}
	
	@PostMapping("/googleLogin")
	@ResponseBody
	public String googleLogin(String email, String nickName, String social, String token,Model model) throws Exception {
		
		int checkEmail = memberDao.confirmEmail(email);
		int checkNickName = memberDao.confirmNickname(nickName);
		System.out.println(token+"?????????");
		Map<String, Object> jsonMap = new HashMap<>();
		
		//????????????o
		if(checkEmail == 1) {
			
			MemberDTO member = memberDao.getMemberByEmail(email);
			
			if (member != null) {
				memberDao.updateLastLogin(member);
				MemberDTO ProfileMember = new FileUtils().setMemberProfileImagePath(member);
				SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken
					(ProfileMember, "[Protected]", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
			}
			
			jsonMap.put("login_result", "/");
			
		//????????????x ??????????????? o
		}else if(checkEmail == -1 && checkNickName == 1) {
			model.addAttribute("email",email);
			model.addAttribute("nickName", nickName);
			model.addAttribute("token",token);
			model.addAttribute("social",social);
			
			jsonMap.put("login_result", "/loginAdd");
			jsonMap.put("email", email);
			jsonMap.put("nickName", nickName);
			jsonMap.put("token", token);
			jsonMap.put("social", social);
			
		//???????????? x ??????????????? x
		}else {
			MemberDTO mDto = new MemberDTO();
			mDto.setMem_email(email);
			mDto.setMem_nickname(nickName);
			mDto.setMem_pw(token);
			mDto.setMem_register_path(social);
			mDto.setMem_email_yn("Y");
			
			int result = 0;
			result = memberDao.insertMember(mDto);
			
			if (result > 0) {
				System.out.println("??????????????????");
				MemberDTO memberByEmail = memberDao.getMemberByEmail(email);
				MemberDTO ProfileMember = new FileUtils().setMemberProfileImagePath(memberByEmail);
				
				SecurityContextHolder.getContext()
				.setAuthentication(new UsernamePasswordAuthenticationToken
				(ProfileMember, "[Protected]", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))));
			}
			
			jsonMap.put("login_result", "/");
		}
		
		JSONObject json = new JSONObject(jsonMap);
		return json.toJSONString();
	}
	@RequestMapping("/naver_callback")
	public String naver_callback() {
		
		return "awesomejump/naver_callback";
	}
	
	@RequestMapping("/recentView")
	public String recentView(HttpServletRequest request, Model model) throws Exception {
		Cookie[] cookie = request.getCookies();
		if(cookie != null){	
			// ?????? ?????? ???
	   		/*
			for(int i = 0; i < cookie.length; i++) {
	   			String name = cookie[i].getName();
	   			if(name.indexOf("Name")!=-1){
	   				
	    			String value = cookie[i].getValue();
	    			//String item = URLDecoder.decode(value,"UTF-8");
	    			
	    			System.out.print(name);
	    			System.out.println(value);
	    			}
				}
	   		 */	
   		// ?????? ??????
	   		ProductDTO pDto = new ProductDTO();
   			List<ProductDTO> recentView = new ArrayList<>(); 
   			
	   		for(int i = cookie.length-1;0<=i;i--) {
	   			String name = cookie[i].getName();
	   			
	   			if(name.indexOf("recent")!=-1){
	   				
	    			String value = cookie[i].getValue();
	    			//String item = URLDecoder.decode(value,"UTF-8");
	    			
	    			//System.out.print(name);
	    			//System.out.println(value);
	    			
	    			pDto = memberDao.recentView(value);
	    			
	    			ProImageDTO productImage = new ProImageDTO();
	    			String imagePath = "";
	    			
	    			try {
	    				productImage = productService.getImageList(pDto.getProduct_no()).get(0);
		    			
		    			imagePath = productImage.getPro_image_path() +
								"/" + productImage.getPro_image_uuid() + 
								"_"   + productImage.getPro_image_name();
		    			
		    			//System.out.println(imagePath);
		    			
		    			pDto.setProduct_thumb_img(imagePath);
		    			
					} catch (Exception e) {
						System.out.println("????????????");
					}
	    			
	    			
	    			recentView.add(pDto);
	    			
	   			}
			}
	   		model.addAttribute("recentView",recentView);
	   		//System.out.println(recentView.toString());
		}
		return "awesomejump/recentView";
	}
	
	@RequestMapping("/interest")
	public String interest(@AuthenticationPrincipal MemberDTO member, Model model, Criteria cri) throws Exception {
		//System.out.println(member.toString());
		
		List<ProductDTO> pDto = new ArrayList<ProductDTO>();
		pDto = memberDao.interest(member.getMem_no(), cri);
		//System.out.println(pDto);
		for(int i = 0 ; i < pDto.size(); i ++) {
			int No = pDto.get(i).getProduct_no();
			
			ProImageDTO productImage = new ProImageDTO();
			String imagePath = "";
			
			try {
				productImage = productService.getImageList(No).get(0);
    			
    			imagePath = productImage.getPro_image_path() +
						"/" + productImage.getPro_image_uuid() + 
						"_"   + productImage.getPro_image_name();
    			
    			//System.out.println(productImage.getPro_image_name() + " = " + imagePath);

    			pDto.get(i).setProduct_thumb_img(imagePath);
			} catch (Exception e) {
			}
		}
		
		model.addAttribute("interest",pDto);
		model.addAttribute("mem_no",member.getMem_no());
		//model.addAttribute("pageMaker", new PageDTO(cri, 30));
		int total = memberDao.interestTotalCount(member.getMem_no());
		model.addAttribute("pageMaker", new PageDTO(cri,total));
		return "awesomejump/interest";
	}
	
	@PostMapping("/deleteInter")
	public String deleteInter(int mem_no, int pno, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProInterestDTO interDto = new ProInterestDTO();
		interDto.setMem_no(mem_no);
		interDto.setProduct_no(pno);
		
		int message = memberDao.deleteInter(interDto);
		
		System.out.println(message);

		Map<String, Object> jObjMap = new HashMap<>();
		jObjMap.put("message", message);
		
		JSONObject jObj = new JSONObject(jObjMap);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jObj);
		return null;
	}
	
	@Transactional
	@PostMapping("/selDeleteInter")
	public @ResponseBody String selDeleteInter(@RequestParam(value="pno[]") List<Integer> pno, @RequestParam(value="mem_no") int mem_no, HttpServletResponse response) throws Exception {
		int message = 0;
		
		//System.out.println("pno" + pno);
		//System.out.println("pno.size()"+pno.size());
		
		for(int i = 0; i < pno.size(); i++) {
			ProInterestDTO interDto = new ProInterestDTO();
			interDto.setMem_no(mem_no);
			interDto.setProduct_no(pno.get(i));
			
			message = memberDao.deleteInter(interDto);
			if(message != 1) {
				System.out.println("???????????? ?????? ?????? pno = " +pno.get(i));
			}
		}
		
		Map<String, Object> jObjMap = new HashMap<>();
		jObjMap.put("message", message);
		
		JSONObject jObj = new JSONObject(jObjMap);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jObj);
		return null;
		
		
		}
	@RequestMapping("/coupon")
	public String coupon(@AuthenticationPrincipal MemberDTO member, Model model) throws Exception {
		int total = memberDao.couponTotal(member.getMem_no());
		model.addAttribute("total", total);
		
		// ????????????
		List<CouponMemDTO> p_couponList = new ArrayList<CouponMemDTO>();
		
		CouponMemDTO p_cDto = new CouponMemDTO();
		
		p_cDto.setMem_no(member.getMem_no());
		
		p_couponList = memberDao.couponN(p_cDto);
		
		model.addAttribute("p_couponList", p_couponList);
		
		// ?????? ?????????
		List<CouponMemDTO> imp_couponList = new ArrayList<CouponMemDTO>();
		
		CouponMemDTO imp_cDto = new CouponMemDTO();
		
		imp_cDto.setMem_no(member.getMem_no());
		
		imp_couponList = memberDao.couponY(imp_cDto);
		
		model.addAttribute("imp_couponList", imp_couponList);
		return "/awesomejump/coupon";
	}
	
	@RequestMapping("/point")
	public String point(@AuthenticationPrincipal MemberDTO member, Model model) throws Exception {
		
		int mem_point = memberDao.selPoint(member.getMem_no());
		model.addAttribute("mem_point", mem_point);
		
		List<PointListDTO> pointList = new ArrayList<PointListDTO>();
		pointList = memberDao.selPointList(member.getMem_no());
		
		model.addAttribute("pointList", pointList);
		return "/awesomejump/point";
	}
	
	@PostMapping("/point")
	public @ResponseBody List<PointListDTO> point(@RequestParam(value="startNum") int startNum, @AuthenticationPrincipal MemberDTO member, HttpServletResponse response) throws Exception{
		int cnt = 9;
		startNum = startNum+1;
		
		List<PointListDTO> addPointList = new ArrayList<PointListDTO>();
		addPointList = memberDao.selAddPointList(member.getMem_no(), startNum, cnt);
		System.out.println("?????? => " + addPointList);
		System.out.println("??????2 =>" + addPointList.size());
		
//		JSONObject obj = new JSONObject();
//		
//		try {
//			JSONArray jArray = new JSONArray();
//			for(int i = 0; i < addPointList.size(); i++) {
//				JSONObject sObject = new JSONObject();
//				sObject.put("order_info_name", addPointList.get(i).getOrder_info_name());
//				sObject.put("order_info_price", addPointList.get(i).getOrder_info_price());
//				sObject.put("point_amount", addPointList.get(i).getPoint_amount());
//				sObject.put("point_au", addPointList.get(i).getPoint_au());
//				sObject.put("point_date", addPointList.get(i).getPoint_date());
//				jArray.add(sObject);
//			}
//			obj.put("result", jArray);
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		obj.put("addPointList", addPointList);
//		response.setContentType("application/x-json; charset=UTF-8");
//		response.getWriter().print(obj);

		return addPointList;
	}
	
	@RequestMapping("/forgotPassword")
	public String getForgotPassword() {
		
		
		return "/awesomejump/forgotPassword";
	}
	
	@Transactional
	@PostMapping("/forgotPassword")
	public @ResponseBody String postForgotPassword(@RequestParam(value="email") String email, HttpServletResponse response) throws Exception {
		
		String emailResult = "";
		MemberDTO mDto = memberDao.selectMember(email);

		Map<String, Object> jObjMap = new HashMap<>();
		
		if(mDto == null) {
			
			emailResult = "???????????? ?????? ????????? ????????????.";
			
			jObjMap.put("message", emailResult);
			jObjMap.put("result", 0);
			
			JSONObject jObj = new JSONObject(jObjMap);
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jObj);
			return null;
			
		}else if(!mDto.getMem_register_path().equals("DEFAULT")) {
			
			emailResult = "????????? ???????????? ???????????????.";
			jObjMap.put("message", emailResult);
			jObjMap.put("result", 1);
			
			JSONObject jObj = new JSONObject(jObjMap);
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jObj);
			return null;
			
		}else {
		
		String setfrom = "ksoyoun95@naver.com";
		String tomail = email; // ?????? ?????? ?????????
		String title = "awesomejump ?????? ???????????? ?????????"; // ??????
		
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', 
				'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
				'L', 'M', 'N', 'O', 'P', 'Q'}; 
		
		int idx = 0; 
		StringBuffer sb = new StringBuffer(); 
		//System.out.println("charSet.length :::: "+charSet.length); 
		for (int i = 0; i < 15; i++) {
			idx = (int) (charSet.length * Math.random()); 
			//System.out.println("idx :::: "+idx); 
			sb.append(charSet[idx]);
		}
		System.out.println("?????? ???????????? ==> " +sb);
			
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, "UTF-8");

			messageHelper.setFrom(setfrom); // ??????????????? ???????????? ??????????????? ??????
			messageHelper.setTo(tomail); // ???????????? ?????????
			messageHelper.setSubject(title); // ??????????????? ????????? ????????????
			messageHelper.setText(
					"<html>"
					+ "<body><div style='text-align: center;'>"
					+ "<img src='https://ifh.cc/g/FZqwaM.png'>"
					+ "<h1>awesomeJump ?????????????????? ?????????.</h1>"
					+ "<h3>?????????????????? : " + sb + "</h3>"
					+ "<h3>????????? ??? '??????????????? -> ???????????? -> ???????????? ??????'?????? ??????????????? ????????? ????????????</h3>"
					+ "</div></body>"
					+ "</html>"
					,true); // ?????? ??????

			mailSender.send(message);
			
			String password = pwdEncoder.encode(sb);
			System.out.println("????????? ??? pw ==> "+ password);
			memberDao.updatePassword(email,password);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		emailResult = "???????????? ?????? ??????????????? ?????????????????????.";
		jObjMap.put("message", emailResult);
		jObjMap.put("result", 2);
		
		JSONObject jObj = new JSONObject(jObjMap);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jObj);
		return null;
		
		}
	}
}
