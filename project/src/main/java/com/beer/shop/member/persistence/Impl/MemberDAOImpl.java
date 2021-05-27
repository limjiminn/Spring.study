package com.beer.shop.member.persistence.Impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.persistence.MemberDAO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	SqlSession sql;
	@Override
	public void inserMember(MemberDTO mDto) throws Exception {
		sql.insert("memberMapper.insertMember", mDto);
		
	}
	

}
