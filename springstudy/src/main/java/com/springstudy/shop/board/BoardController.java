package com.springstudy.shop.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springstudy.shop.board.domain.BoardAttachDTO;
import com.springstudy.shop.board.domain.BoardDTO;
import com.springstudy.shop.board.domain.Criteria;
import com.springstudy.shop.board.domain.PageDTO;
import com.springstudy.shop.board.service.IBoardService;

@Controller  //어노테이션을 붙여줘야 컨트롤러로 인식한다.
@RequestMapping ("/board")  //클래스영역과 메소드 영역에 
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired   //자동으로 객체생성
	private IBoardService service;
	
	@RequestMapping("/list")
	public void listAll(Criteria cri, Model model) throws Exception {
		logger.info("show all list ........" + cri);
		
		model.addAttribute("list", service.listAll(cri));
		
		int total = service.getTotalCnt(cri);
		logger.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	//등록부분은 폼이 보여야한다.
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("register get.........");
	}
	
	//경로는 똑같아도 전혀 다른 기능이 된다.,.
	//한두개 받을땐  @param 사용하자
	//게시물 등록
	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public String registerPOST(BoardDTO bDto, RedirectAttributes rttr) throws Exception{
		logger.info("register POST.........");  //redirectAttributes : redirect를 처리할때 사용(일회성)
		logger.info("/register====> " + bDto);	
		
		logger.info("====================================");
		//게시물이 등록될때 해당되는 Attach
		if (bDto.getAttachList() != null) {
			bDto.getAttachList().forEach(attach -> logger.info("" + attach));
		}
		logger.info("확인");
		logger.info("====================================");
		service.register(bDto);
		//addFlashAttribute: 리다이렉트 직전 플래시에 저장하는 메소드(리다이렉트 이후 소멸)
		//내부적으로 HttpSession을 이용해 처리
		rttr.addFlashAttribute("result" , bDto.getBno());
		//F5 누르면 똑같은거 계속진행 방지(화면전환)
		//재전송 처리 : redirect
		//redirect를 사용하지않으면 처리가 완료된 후 다시 동일 내용 전송이 된다.
		//그래서 브라우저의 URL을 이동하는 방식을 이용한다.
		return "redirect:/board/list";
	}
	//게시물 조회
//	@RequestMapping(value = "/read", method = RequestMethod.GET )
//	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
//		model.addAttribute("board",service.read(bno));
//		
//	}
//	
//	//게시물 수정
//	@RequestMapping(value = "/modify", method = RequestMethod.POST)
//	public String modifyPOST(BoardDTO bDto, RedirectAttributes rttr) throws Exception{
//		logger.info("modify post ...........");
//		
//		if (service.modify(bDto)) {
//			rttr.addFlashAttribute("result", "success");
//		}
//		
//		return "redirect:/board/list";
//	}
	//조회랑 수정 통합
	@RequestMapping(value = {"/read", "/modify"}, method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri,  Model model) throws Exception{
		logger.info("/read or /modify....................");
		
		
		model.addAttribute("board",service.read(bno));
		model.addAttribute("pageNum", cri.getPageNum());
		model.addAttribute("amount", cri.getAmount());
		model.addAttribute("type", cri.getType());
		model.addAttribute("keyword", cri.getKeyword());
		
		logger.info("pageNum1 : " + cri.getPageNum());
		logger.info("keyword : " + cri.getKeyword());
		
		
	}
	
	//게시물 삭제
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("remove...............");
		
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}	
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardDTO bDto, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("modify post............." + cri);
		
		if (service.modify(bDto)) {
			rttr.addFlashAttribute("result", "success");
		}	
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			
			rttr.addAttribute("type", cri.getType());
			rttr.addAttribute("keyword", cri.getKeyword());
			
			logger.info("pageNum : " + cri.getPageNum());
			logger.info("pageNum : " + cri.getKeyword());
			
		return "redirect:/board/list";
	}
	
	@GetMapping(value = "/getAttachList",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<BoardAttachDTO>> getAttachList(int bno){
		logger.info("getAttachList : " + bno);
		
		return new ResponseEntity<>(service.getAttachList(bno), HttpStatus.OK);
	}
}
