package com.springstudy.shop.persistence.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.shop.member.domain.MemberDTO;
import com.springstudy.shop.persistence.IMemberDAO;

@Repository // 무조건 달아줘야 한다 DB쿼리 연동엔 필수 
public class MemberDAOImpl implements IMemberDAO{
	
	@Autowired // 스프링이 알아서 객체 생성되어있는거 가져다 준다
	private SqlSession sqlSession;
	
	private static final String namespace = "com.springstudy.shop.memberMapper"; // MemberMapper.xml namespce와 같은 값
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
		return sqlSession.selectOne(namespace + ".selMember", userid);
	}

	@Override
	public MemberDTO selLoginInfo(String userid, String userpw) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
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
		
		return sqlSession.delete(namespace + ".deMember", userid);
	}
}
