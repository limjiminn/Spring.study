package com.beer.shop;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
//	@Autowired
//	private IBoardService service;
	
	@RequestMapping("/list")
	public void listAll() throws Exception {
		logger.info("show all list ......." );
		
		
		
	}
}
