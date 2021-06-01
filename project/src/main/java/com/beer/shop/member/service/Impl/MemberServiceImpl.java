package com.beer.shop.member.service.Impl;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.persistence.MemberDAO;
import com.beer.shop.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO mDao;

	@Override
	public void insertMember(MemberDTO mDto) throws Exception {
		mDao.insertMember(mDto);
	}
	//아이디 조회
	@Override
	public int idchk(String userid) throws Exception {
		
		int result = mDao.idchk(userid);
		
		return result;
	}
	//로그인
	@Override
	public MemberDTO selLoginInfo(MemberDTO mDto) throws Exception {
	
		return mDao.selLoginInfo(mDto);
	}
	//회원 수정
	//Controller에서 보내는 파라미터들을 update(MemberDTO mDto)로 받는다.
	@Override
	public void update(MemberDTO mDto) throws Exception {
		//받은 dto를 dao로 보내준다.
		mDao.update(mDto);
	}
	//회원 삭제
	@Override
	public void delete(MemberDTO mDto) throws Exception {
		
		mDao.delete(mDto);
	}
	//로그아웃
//	public void logout(HttpServletResponse response) throws Exception{
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		
//	}
	//회원 정보조회
	@Override
	public MemberDTO selmember(String userid) throws Exception {
		System.out.println("정보 조회");
		return mDao.selmember(userid);
	}
	
	

}
