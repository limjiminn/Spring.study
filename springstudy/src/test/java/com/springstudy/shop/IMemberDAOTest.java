package com.springstudy.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springstudy.shop.domain.MemberDTO;
import com.springstudy.shop.persistence.IMemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
public class IMemberDAOTest {
	
private static final Logger logger = LoggerFactory.getLogger(IMemberDAOTest.class);
	
	@Autowired
	private IMemberDAO mDao;
	
	@Test
	public void testTime() throws Exception{
		logger.info("mDao.getTime() ===> " + mDao.getTime());
	}
	//회원 가입
	@Test
	public void testInsetMember() throws Exception{
		MemberDTO mDto = new MemberDTO();
		mDto.setUserid("user04");
		mDto.setUserpw("user04");
		mDto.setUsername("USER04");
		mDto.setEmail("user04@naver.com");
		
		mDao.insertMember(mDto);
	}
	//회원 조회
	@Test
	public void selMember() throws Exception {
		MemberDTO mDto = mDao.selMember("user03");
		logger.info(mDto.toString());
	}
	//회원정보 수정 삭제 
	@Test
	public void selLoginInfo() throws Exception{
		MemberDTO mDto = mDao.selLoginInfo("user03", "user03");
		logger.info(mDto.toString()); 
	}
	
	//회원 정보 수정
	@Test
	public void modifyMember() throws Exception{
		MemberDTO mDto = new MemberDTO();
		mDto.setUserid("user04");
		mDto.setUserpw("1234");
		mDto.setEmail("hhh@naver.com");
		
		mDao.update(mDto);
	}
	
	//회원 정보 삭제
	@Test
	public void deMember() throws Exception {
		logger.info("확인");
		mDao.delete("user04");
	}

}
