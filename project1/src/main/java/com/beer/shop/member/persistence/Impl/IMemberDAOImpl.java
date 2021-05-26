package com.beer.shop.member.persistence.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.persistence.IMemberDAO;

public class IMemberDAOImpl implements IMemberDAO{

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "com.beer.shop.member.memberMapper";
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberDTO mDto) {
		sqlSession.insert(namespace + ".insertMember", mDto);
	}

	@Override
	public MemberDTO selMember(String userid) throws Exception {
		return sqlSession.selectOne(namespace +".selMember", userid);
	}

	@Override
	public MemberDTO selLoginInfo(String userid, String userpw) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		return sqlSession.selectOne(namespace + ".selLoginfo", paramMap);
	}

	@Override
	public int update(MemberDTO mDto) throws Exception {
		return sqlSession.update(namespace + ".modifyMember", mDto);
	}

	@Override
	public int delete(String userid) throws Exception {
		return sqlSession.delete(namespace + ".deMember",userid);
	}
}
