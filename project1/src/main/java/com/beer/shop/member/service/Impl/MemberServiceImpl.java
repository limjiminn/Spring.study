package com.beer.shop.member.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.persistence.IMemberDAO;
import com.beer.shop.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private IMemberDAO mDao;
	
	@Override
	public void insertMember(MemberDTO mDto) throws Exception {
		mDao.insertMember(mDto);
	}

}
