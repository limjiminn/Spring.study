package com.springstudy.shop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class BoardControllerTests {

	private static final Logger logger = LoggerFactory.getLogger(BoardControllerTests.class);
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	
	@Test
	public void testList() throws Exception{
		logger.info("" + mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
											.andReturn()
											.getModelAndView()
											.getModelMap());
	}
	 //게시물 등록
	@Test
	public void testResister() throws Exception {						
		String resultPage = mockMvc
								.perform(MockMvcRequestBuilders
								.post("/board/register")  //가상으로 실행할 URI(빌더패턴)
								.param("title", "테스트 새글 제목")    //하나씩 파라미터로 지정
								.param("content", "테스트 새글 내용 입니다")
								.param("writer", "user01"))
								.andReturn()
								.getModelAndView()
								.getViewName();
		logger.info(resultPage);
	}
	//게시물 조회
	@Test
	public void testRead() throws Exception{
		logger.info(""+mockMvc.perform(MockMvcRequestBuilders
										.get("/board/read")
										.param("bno", "1"))
										.andReturn()
										.getModelAndView()
										.getModelMap());
		
	}
	
	//게시물 수정
	@Test
	public void testModify() throws Exception{
		logger.info(""+mockMvc.perform(MockMvcRequestBuilders
										.post("/board/modify")
										.param("bno", "1")
										.param("title", "수정된 테스트 새글 제목")
										.param("content", "수정된 테스트 새글 내용")
										.param("writer", "user01"))
										.andReturn()
										.getFlashMap());
		
	}
	
	
	//게시물 삭제
	@Test
	public void testRemove() throws Exception{
		logger.info(""+mockMvc.perform(MockMvcRequestBuilders
										.post("/board/remove")
										.param("bno", "3"))
										.andReturn()
										.getModelAndView()
										.getModelMap());
		
	}
}
