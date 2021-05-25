package com.beer.shop.board;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.shop.board.domain.BoardDTO;
import com.beer.shop.board.domain.Criteria;
import com.beer.shop.board.domain.PageDTO;
import com.beer.shop.board.service.IBoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private IBoardService service;
	
	@RequestMapping("/list")
	public void listAll(Criteria cri, Model model) throws Exception {
		logger.info("show all list ......." );
		
		model.addAttribute("list", service.listAll(cri));
		
		int total = service.getTotalCnt(cri);
		logger.info("total : " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("register get......");
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardDTO bDto, RedirectAttributes rttr) throws Exception{
		logger.info("register POST.......");
		logger.info("/register ===>" + bDto);
		
		service.register(bDto);
		
		rttr.addFlashAttribute("result", bDto.getBno());
		
		return "redirect:/board/list";
		
	}
	//조회 수정 통합
	@RequestMapping(value = {"/read", "/modify"}, method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
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
	
}
