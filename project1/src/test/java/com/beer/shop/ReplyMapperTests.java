package com.beer.shop;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beer.shop.board.domain.Criteria;
import com.beer.shop.board.domain.ReplyDTO;
import com.beer.shop.board.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	private int[] bnoArr = {1736712,1736711,1736710,1736709,1736702};
	@Autowired
	private ReplyMapper mapper;
	
	
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1, 10).forEach(i ->{
			
			ReplyDTO replyDto = new ReplyDTO();
			
			replyDto.setBno(bnoArr[i % 5]);
			replyDto.setReplytext("댓글 테스트 " + i);
			replyDto.setReplyer("replyer" + i);
			
			mapper.insert(replyDto);
		});;
	}
	
	@Test
	public void testRead() {
		int targetRno = 5;
		
		ReplyDTO replyDto = mapper.read(targetRno);
		
		log.info(replyDto);
	}
	
	@Test
	public void testDelete() {
		int targetRno = 1;
		
		int result = mapper.delete(targetRno);
		log.info("delete result : " + result);
	}
	
	@Test
	public void testUpdate() {
		int targetRno = 10;
		
		ReplyDTO replyDTO = mapper.read(targetRno);
		
		replyDTO.setReplytext("수정 테스트");
		
		int count = mapper.update(replyDTO);
		log.info("update count : " + count);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyDTO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
}
