package com.beer.shop.board;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beer.shop.board.domain.Criteria;
import com.beer.shop.board.domain.ReplyDTO;
import com.beer.shop.board.domain.ReplyPageDTO;
import com.beer.shop.board.service.IReplyService;


@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private IReplyService service;
	
	private static final Logger log = LoggerFactory.getLogger(ReplyController.class);
	
	@PostMapping(value = "/new", consumes = "application/json",
				produces = {MediaType.TEXT_PLAIN_VALUE})
	//json타입{"key":"value"}
	//상태코드값까지 반환						//json타입으로 반환될때
	//등록								
	public ResponseEntity<String> create(@RequestBody ReplyDTO replyDto , Model model){
		log.info("ReplyDTO : " + replyDto);
		int insertCount = service.register(replyDto);
		model.addAttribute("reply",insertCount);
		log.info("Reply Insert Count : " + insertCount);
		
		return insertCount == 1? 
			  new ResponseEntity<String>("success", HttpStatus.OK)
			: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//댓글 목록 갖고오기
	@GetMapping(value = "/pages/{bno}/{page}",
				produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
							//수정
	public ResponseEntity<ReplyPageDTO> getList(
	//getList()는 Criteria를 이용해서 파라미터를 수집하는데
	//{bno}/{page} 의 page값은 Criteria를 생성해서 직접처리해야한다.
	//게시물의 번호는 @PathVariable을 이용해서 파라미터로 처리하고 브라우저에서 테스트
						@PathVariable("page") int page,
						@PathVariable("bno") int bno
						){
		Criteria cri = new Criteria(page, 10);
		log.info("get Reply List bno : " + bno);
		log.info("cri : " + cri);
		
													//수정
		return new ResponseEntity<>(service.getListPage(cri, bno),HttpStatus.OK);
	}
	
	//조회 (댓글 상세)
	@GetMapping(value = "/{rno}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<ReplyDTO> read(@PathVariable("rno") int rno, Model model){
		log.info("get : " + rno);
		model.addAttribute("reply", service.read(rno));
		log.info("rno : " + service.read(rno));
		return new ResponseEntity<ReplyDTO>(service.read(rno), HttpStatus.OK);
		
	}
	
	//삭제
	@DeleteMapping(value = "/{rno}",produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		log.info("remove : " + rno);
		
		return service.remove(rno) == 1?
				new ResponseEntity<String>("success", HttpStatus.OK)
			  : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	//수정
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}
			,value = "/{rno}",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify(
		//실제 수정되는 데이터는 JSON포맷이므로 @RequestBody 사용
			@RequestBody ReplyDTO replyDto,
			@PathVariable("rno") int rno){
		replyDto.setRno(rno);
		
		log.info("rno : " + rno);
		log.info("modify : " + replyDto);
		
		return service.modify(replyDto) == 1?
				new ResponseEntity<String>("success", HttpStatus.OK)
			  : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
