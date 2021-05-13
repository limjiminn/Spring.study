package com.springstudy.shop;

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
public class MockMvcTest {
	private static final Logger logger = LoggerFactory.getLogger(MockMvcTest.class);

	//선언만하면 지가알아서 객체까지생성해서 쓸수있게 주입해준다
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	//먼저 선행작업할때
	@Before
	public void setup() {  //빌더패턴 : 제이쿼리에서는 체이닝기법처럼 사용
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); 
	}
	
	//가상의 웹환경에서 동작하게한는것(ex04)
	@Test
	public void test() throws Exception {						
		String resultPage = mockMvc
								.perform(MockMvcRequestBuilders.get("/sample/ex04")  //가상으로 실행할 URI(빌더패턴)
								.param("superName", "홍길동")    //하나씩 파라미터로 지정
								.param("age", "18")
								.param("page", "9"))
								.andReturn()
								.getModelAndView()
								.getViewName();
		logger.info(resultPage);
	}
	
}
