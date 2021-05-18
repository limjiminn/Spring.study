package com.springstudy.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springstudy.shop.domain.BoardDTO;
import com.springstudy.shop.service.IBoardService;

@Controller  //어노테이션을 붙여줘야 컨트롤러로 인식한다.
@RequestMapping ("/board")  //클래스영역과 메소드 영역에 
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired   //자동으로 객체생성
	private IBoardService service;
	
	@RequestMapping("/list")
	public void listAll(Model model) throws Exception {
		logger.info("show all list ........");
		
		model.addAttribute("list", service.listAll());
	}
	//등록부분은 폼이 보여야한다.
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public void regitsterGET() {
		logger.info("register get.........");
	}
	
	//경로는 똑같아도 전혀 다른 기능이 된다.,.
	//한두개 받을땐  @param 사용하자
	//게시물 등록
	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public String regitsterPOST(BoardDTO bDto, RedirectAttributes rttr) throws Exception{
		logger.info("register POST.........");  //redirectAttributes 는 POST방식으로 전달
		logger.info("/register====> " + bDto);	
		
		service.register(bDto);
		//addFlashAttribute: 리다이렉트 직전 플래시에 저장하는 메소드(리다이렉트 이후 소멸)
		rttr.addFlashAttribute("result" , bDto.getBno());
		//F5 누르면 똑같은거 계속진행 방지(화면전환)
		return "redirect:/board/list";
	}
	//게시물 조회
	@RequestMapping(value = "/read", method = RequestMethod.GET )
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute("board",service.read(bno));
		
	}
	
	//게시물 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardDTO bDto, RedirectAttributes rttr) throws Exception{
		logger.info("modify post ...........");
		
		if (service.modify(bDto)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
	}
	//게시물 삭제
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("remove...............");
		
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list";
		
	}
}
