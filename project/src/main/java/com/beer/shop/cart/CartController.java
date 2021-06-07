package com.beer.shop.cart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beer.shop.cart.domain.CartDTO;
import com.beer.shop.cart.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService service;
	//@ModelAttribute는 sumit된 form의 내용을 저장해서 전달 받거나, 다시 뷰로 넘겨서 출력하기 위해
	//사용되는 오브젝트
	//도메인 오브젝트나 DTO의 프로퍼티에 요청 파라미터를 바인딩해서 한번에 받으면 @ModelAttribute라고 볼수있다.
	//ModelAttribute는 컨트롤러가 리턴하는 모델에 파라미터로 전달한 오브젝트를 자동을 추가해준다.
	@RequestMapping("/insert")
	public String insert(@ModelAttribute CartDTO cDto, HttpSession session)throws Exception{
		//로그인 여부를 체크하기 위해 세션에 저장된 아이디 확인
		String userid = (String) session.getAttribute("userid");
		if(userid == null) {
			return "redirect:/member/login";
		}
		cDto.setUserid(userid);
		service.insert(cDto);
		return null;
	}
}
