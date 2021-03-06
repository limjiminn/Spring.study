package com.beer.shop.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	
	//다른컨트롤에서 나오는에러 처리 
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		//{}안으로 ex객체가 전달된다.
		logger.error("Exception.....{}", ex);
		
		//키값이 필요할땐 이렇게
		model.addAttribute("exception", ex); 
		
		logger.error("model.....{}", model);
		
		return "error_page";
	}
	
	//404오류시 
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		return "custom404";
	}
}
