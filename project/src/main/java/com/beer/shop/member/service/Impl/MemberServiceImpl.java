package com.beer.shop.member.service.Impl;

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
	public MemberDTO selMember(String userid) throws Exception {
		return mDao.selMember(userid);
	}
	@Override
	public MemberDTO selLoginInfo(String userid, String userpw) throws Exception {
	
		return mDao.selLoginInfo(userid, userpw);
	}
	@Override
	public int update(MemberDTO mDto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(String userid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
