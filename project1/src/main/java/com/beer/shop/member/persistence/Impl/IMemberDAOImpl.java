package com.beer.shop.member.persistence.Impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.persistence.IMemberDAO;

public class IMemberDAOImpl implements IMemberDAO{
	
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void create(MemberDTO mDto) throws Exception {
		
	}

	@Override
	public MemberDTO read(Integer bno) throws Exception {
		return null;
	}

	@Override
	public int update(MemberDTO mDto) throws Exception {
		return 0;
	}

	@Override
	public int delete(Integer bno) throws Exception {
		return 0;
	}

}
