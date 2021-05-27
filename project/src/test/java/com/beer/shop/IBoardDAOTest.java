package com.beer.shop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beer.shop.board.domain.BoardDTO;
import com.beer.shop.board.domain.Criteria;
import com.beer.shop.board.persistence.IBoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
public class IBoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(IBoardDAOTest.class);
	
	@Autowired
	private IBoardDAO bDao;
	
//	@Test
//	public void testCreate() throws Exception{
//		BoardDTO bDto = new BoardDTO();
//		bDto.setTitle("새로운글을 등록합니다.");
//		bDto.setContent("새로운 글등록");
//		bDto.setWriter("user02");
//		bDao.create(bDto);
//	}
	
//	@Test
//	public void testRead() throws Exception{
//		logger.info(bDao.read(1).toString());
//	}
	
//	@Test
//	public void testUpdate() throws Exception{
//		BoardDTO bDto = new BoardDTO();
//		bDto.setBno(1);
//		bDto.setTitle("수정된 글입니다");
//		bDto.setContent("수정된 글");
//		bDao.update(bDto);
//	}
	
//	@Test
//	public void testDelete() throws Exception{
//		bDao.delete(1);
//	}
	
//	@Test
//	public void testlistAll() throws Exception{
//		logger.info(bDao.listAll().toString());
//	}
	
	@Test
	public void textListCriteria() throws Exception {
		Criteria cri = new Criteria();
		//한페이지당 10개씩
		cri.setPageNum(1);
		cri.setAmount(10);
		
		List<BoardDTO> list = bDao.listAll(cri);
		
		list.forEach(board -> logger.info(board.getBno()+ " : " + board.getContent()));
		
	}
	
	@Test
	public void testSearch() throws Exception {
		Criteria cri = new Criteria();
		cri.setKeyword("테스트 새글 제목13수정");
		cri.setType("TC");  //검색어 옆 타이틀 + 컨텐츠
		
		List<BoardDTO> list = bDao.listAll(cri);
		//람다식 사용
		list.forEach(board -> logger.info("" + board));
	}
}
