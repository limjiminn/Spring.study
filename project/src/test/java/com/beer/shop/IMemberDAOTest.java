package com.beer.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
public class IMemberDAOTest {
	
private static final Logger logger = LoggerFactory.getLogger(IMemberDAOTest.class);
	
	@Autowired
	private MemberDAO mDao;
	
	@Test
	public void testTime() throws Exception{
		logger.info("mDao.getTime() ===> " + mDao.getTime());
	}
	//회원 가입
	@Test
	public void testInsertMember() throws Exception{
		MemberDTO mDto = new MemberDTO();
		mDto.setUserid("user06");
		mDto.setUserpw("1234");
		mDto.setUsername("배용배");
		mDto.setEmail("user06@naver.com");
		
		mDao.insertMember(mDto);
	}
	//회원 조회
	@Test
	public void selMember() throws Exception {
		MemberDTO mDto = mDao.selMember("user05");
		logger.info(mDto.toString());
	}
	//회원 로그인
	@Test
	public void selLoginInfo() throws Exception{
		MemberDTO mDto = mDao.selLoginInfo("user06", "1234");
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
