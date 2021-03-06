package com.springstudy.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springstudy.shop.domain.SampleDTO;
import com.springstudy.shop.domain.SampleDTOList;
import com.springstudy.shop.domain.TodoDTO;

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
	
	@RequestMapping(value = "/basic", method = RequestMethod.GET)
	public void basicGet() {
		logger.info("basicGet1.................");
		log.info("basicGet2....................");
	}
	
	//get방식으로 사용됨
	@RequestMapping(value = "/basic1", method = RequestMethod.POST)
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
		
		sDto.setSuperName(superName);
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
		log.info("=================================");
		for(String idStr : ids) {
			log.info(idStr);
		}
		
		log.info(">=========람다 출력 ================");
		//컬렉션개체들은 스트링개체가 있다.. 변수 ->
		ids.forEach(idsStr -> {log.info(idsStr);});
		return "ex02List";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos : "+ list);
		
		return "ex02Bean";
	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo : " + todo);
		return "ex03";
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	@RequestMapping("/sampleModel")
	public String sampleModel(SampleDTO sampleDTO, Model model) {
//		SampleDTO sampleDTO = new SampleDTO("홍길동", 10);   //가상
		
		log.info("sampleModel");
		//기초자료형은 이렇게 못뽑아온다
		//개체자료형만 가능
		model.addAttribute(sampleDTO);   //sample.jsp의 ${sampleDTO}
		
		return "/sample/sample";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO sampleDTO,@ModelAttribute("page") int page) {
		//@ModelAttribute("page")를 사용하면 기초자료형도 받아올수있다.,
		log.info("sampleDTO : " +sampleDTO);
		log.info("page : "+ page);
		
		return "/sample/ex04";	
	}
	
	@GetMapping(value = "/doE")
	public String doE(RedirectAttributes rttr) {
		log.info("doE 호출되지만 / doF로 리다이렉트...");
		
		rttr.addFlashAttribute("msg","리다이렉트된 메세지 입니다."); //메세지를 담아서 한번만 뿌려준다.
		
		return "redirect:/sample/doF";
	}
	
	@RequestMapping("/doF")
	public String doF() {
		log.info("doF 호출 됨..............");
		
		return "/sample/redirectAttributes";  //요 jsp로 넘어간다.
	}
	
	//void로 했을경우
	@RequestMapping("/ex05")
	public void ex05() {
		
		log.info("/ex05..................");
	}
	
	//제이슨 포멧 방식
	@RequestMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..............");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setSuperName("홍길동");
		
		return dto;
	}
	
	//맵 반환
	@RequestMapping("/ex06_1")
	public @ResponseBody Map<String, SampleDTO> ex06_1() {
		log.info("/ex06_1..............");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setSuperName("홍길동");
		
		Map<String, SampleDTO> map = new HashMap<>();
		map.put("info", dto);
		
		return map;
		
	}
	//회원 두명의 정보가.. 컬렉션프레임워크 매퍼 공부하기
	@RequestMapping("/ex06_2")
	public @ResponseBody Map<String, List<SampleDTO>> ex06_2() {
		log.info("/ex06_2..............");
		List<SampleDTO> list = new ArrayList<>();
		
		Map<String, List<SampleDTO>> map = new HashMap<>();
		
		SampleDTO dto1 = new SampleDTO();
		dto1.setAge(10);
		dto1.setSuperName("홍길동");
		
		list.add(dto1);
		
		SampleDTO dto2 = new SampleDTO();
		dto2.setAge(18);
		dto2.setSuperName("이몽룡");
		
		list.add(dto2);
		
		map.put("info", list);
		
		return map;	
	}
	//responseEntity: 타입지정,메시지 전달 헤더정보나 데이터를 전달
	@RequestMapping("/ex07")
	public ResponseEntity<String> ex07(){
		
		String msg = "{\"name\":\"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		//HttpStatus.OK : 200
		return new ResponseEntity<String>(msg,header,HttpStatus.OK);
	}
	
	//파일업로드
	@RequestMapping(value = "/exFileUpload", method = RequestMethod.GET)
	public void exFileUpload() {
		log.info("/exFileUpload...............");
		
	}
	
	@RequestMapping(value = "/exUploadPost",method = RequestMethod.POST)
	public void exUploadPost(ArrayList<MultipartFile> files) throws Exception {
		//ArrayList<MultipartFile> files : 배열로 받아오기 (어레이리스트배열을 향상시킨것
		log.info("/exUploadPost...............");
		
		for(MultipartFile multipartFile : files) {
			
		}
		
		//files를 통해서 foreach안에들어오는데
		//file 실행영역에서 갖고오는 임의의 데이터(MultipartFile 타입)
		log.info(">=========람다 출력 ================");
		files.forEach(file -> {
			log.info("------------------------------");
			//파일네임갖고올때 쓰는거
			log.info("fileName : " + file.getOriginalFilename());
			log.info("fileSize : " + file.getSize());
		});
	}
}
