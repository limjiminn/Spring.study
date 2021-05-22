package com.beer.shop;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.beer.shop.domain.BoardDTO;
import com.beer.shop.domain.Criteria;
import com.beer.shop.domain.PageDTO;
import com.beer.shop.service.IBoardService;

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
	}
}
