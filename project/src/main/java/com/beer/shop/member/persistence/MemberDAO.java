package com.beer.shop.member.persistence;

import com.beer.shop.member.domain.MemberDTO;

public interface MemberDAO {

	//회원가입
	public void insertMember(MemberDTO mDto) throws Exception;
	
	public String getTime(); // 원래 abstract final ?
	//아이디 중복조회
	public MemberDTO selMember(String userid) throws Exception;
	//로그인
	public MemberDTO selLoginInfo(MemberDTO mDto) throws Exception;
	//회원수정
	public int update(MemberDTO mDto) throws Exception;
	//회원삭제
	public int delete(String userid) throws Exception;
}
