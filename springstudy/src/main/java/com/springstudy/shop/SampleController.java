package com.springstudy.shop;

import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springstudy.shop.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping("/star")
	public void basic() {
		logger.info("basic1.................");
		log.info("basic2.................");
	}
	
	@RequestMapping("/craft")
	public void basic1() {
		logger.info("basic3.................");
		log.info("basic4.................");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		logger.info("basicGet1.................");
		log.info("basicGet2....................");
	}
	
	//get방식으로 사용됨
	@RequestMapping(value = "/basic1", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicPost() {
		logger.info("basicPost1.................");
		log.info("basicPost2....................");
	}
	
	@RequestMapping(value = "/basic1", method = RequestMethod.GET)
	public void basicGet2() {
		logger.info("basicGet3.................");
		log.info("basicGet4....................");
	}
	//메소드 없을땐 GET방식
	@RequestMapping("/basicOnlyGet")
	public void basicGet3() {
		logger.info("basic get only Get5.................");
		log.info("basic get only Get6....................");
	}
	
	@RequestMapping("/basicOnlyGet1")
	public void basicPost2() {
		logger.info("basic get only Post3.................");
		log.info("basic get only Post4....................");
	}
	
//	@RequestMapping("/ex01")
//	public String ex01(SampleDTO sDto) {
//		logger.info(sDto.getName());
//		logger.info("" + sDto.getAge());
//		logger.info(sDto.toString());  //인포 사용할때 ""사용(문자열로)
//		log.info("" +sDto);
//		return "ex01";
//	}
	
	@RequestMapping("/ex02")
	public String ex02(@RequestParam("name") String superName,
					   @RequestParam("age") int superAge) {
		log.info("superName :" + superName);
		log.info("superAge :" + (superAge + 1));
		SampleDTO sDto = new SampleDTO();
		sDto.setName(superName);
		sDto.setAge(superAge);
		log.info(sDto.toString());
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : " + ids);
		
		for(String id : ids) {
			log.info("ids : " + id);
		}

		return "ex02List";
		
	}
	
	
	
}
