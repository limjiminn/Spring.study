package com.beer.shop.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beer.shop.cart.domain.CartDTO;
import com.beer.shop.cart.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService service;
	
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	//@ModelAttribute는 sumit된 form의 내용을 저장해서 전달 받거나, 다시 뷰로 넘겨서 출력하기 위해
	//사용되는 오브젝트
	//도메인 오브젝트나 DTO의 프로퍼티에 요청 파라미터를 바인딩해서 한번에 받으면 @ModelAttribute라고 볼수있다.
	//ModelAttribute는 컨트롤러가 리턴하는 모델에 파라미터로 전달한 오브젝트를 자동을 추가해준다.
	@RequestMapping("/insert")
	public String insert(@ModelAttribute CartDTO cDto,HttpSession session)throws Exception{
		//로그인 여부를 체크하기 위해 세션에 저장된 아이디 확인
		String userid = (String) session.getAttribute("userid");
//		if(userid == null) {
//			return "redirect:/member/login";
//		}
		
		cDto.setUserid(userid);
		service.insert(cDto);
		return "redirect:/cart/list";
	}
	@RequestMapping("/list")
	public ModelAndView listCart(HttpSession session, ModelAndView mav) throws Exception {
		log.info("==============list ================");
		Map<String, Object> map = new HashMap<>();
		//해시맵은 map(key,value)로 이뤄져 있고,
		//key값은 중복이 불가능 하고 value는 중복이 가능하다.
		//value에 null값도 사용이 가능하다.
		//전달할 정보가 많을 경우는 해시맵을 사용하는것이 좋다.
		//장바구니에 담을 값들이 많기 때문에 여기선 해시맵을 사용한다.
		
		String userid = (String) session.getAttribute("userid");
		log.info("userid : "+ userid);
		//session에 저장된 userid를 getAttribute()메소드를 사용해서 얻어오고 오브젝트
		//타입이기 떄문에 String 타입으로 타입변환 한다.
//		if (userid !=null) {
			//로그인한 상태이면 실행
			List<CartDTO> list =service.listCart(userid); //장바구니 목록
			int sumMoney = service.sumMoney(userid); //금액합계
			
			log.info("list : " + list);
			map.put("sumMoney", sumMoney); //가격
			map.put("list", list);	//목록
			map.put("count", list.size());	//갯수

			mav.setViewName("cart/list"); //이동할 페이지 이름
			mav.addObject("map", map); //데이터 저장
			return mav;
			
			
			/*
			 * }else { //로그인되지 않았을때 return new ModelAndView("member/login", "",null); } }
			 */
			 
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam int cartid) throws Exception {
		service.delete(cartid);
		return "redirect:/cart/list";
	}
	@RequestMapping("/deleteAll")
	public String deleteAll(HttpSession session) throws Exception{
		String userid = (String) session.getAttribute("userid");
		if (userid!=null) {
			service.deleteAll(userid);
		}
		return "redirect:/cart/list";
	}
	
	public String modify(CartDTO cDto) throws Exception{
		service.modifyCart(cDto);
		return "redirect:/cart/list";
	}
}
