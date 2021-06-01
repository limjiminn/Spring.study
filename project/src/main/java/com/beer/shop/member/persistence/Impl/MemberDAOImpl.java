package com.beer.shop.member.persistence.Impl;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beer.shop.member.domain.MemberDTO;
import com.beer.shop.member.persistence.MemberDAO;

@Repository // 무조건 달아줘야 한다 DB쿼리 연동엔 필수 
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired // 스프링이 알아서 객체 생성되어있는거 가져다 준다
	private SqlSession sqlSession;
	
	private static final String namespace = "com.beer.shop.member.mapper.memberMapper"; // MemberMapper.xml namespce와 같은 값
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberDTO mDto) {
		sqlSession.insert(namespace + ".insertMember", mDto);
	}
	//아이디 중복조회
	@Override
	public int idchk(String userid) throws Exception {
		
		int result = sqlSession.selectOne(namespace + ".idchk", userid);
		
		return result;
	}

	@Override
	public MemberDTO selLoginInfo(MemberDTO mDto) throws Exception {
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("userid", userid);
//		paramMap.put("userpw", userpw);
//		return sqlSession.selectOne(namespace + ".selLoginfo", paramMap);
		return sqlSession.selectOne(namespace + ".selLoginfo", mDto);
	}
	//회원 정보수정
	//서비스에서 보낸 파라미터들을 update(MemberDTO mDto)에 담는다.
	@Override
	public void update(MemberDTO mDto) throws Exception {
		//mDto에 담긴 파라미터들은 memberMapper.xml에 memberMapper라는 namespace
		//아이디가 memberUpdate인 쿼리에 파라미터들을 넣어줌
		sqlSession.update(namespace + ".modifyMember", mDto);
	}
	
	@Override
	public void delete(MemberDTO mDto) throws Exception {
		//MemberDTO에 담긴 값들을 보내줌
		//그럼 xml에서 memberMapper.deMember에 보면
		// #{userid},#{userpw}에 파라미터값 매칭
		 sqlSession.delete(namespace + ".deMember", mDto);
	}

	@Override
	public MemberDTO selmember(String userid) throws Exception {

		return sqlSession.selectOne(namespace + ".selmember", userid);
	}

	
}
