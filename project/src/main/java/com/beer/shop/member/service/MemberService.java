package com.beer.shop.member.service;

import com.beer.shop.member.domain.MemberDTO;

public interface MemberService {
	//회원가입
	public void insertMember(MemberDTO mDto) throws Exception;
	//아이디 중복 검사
	public int idchk(String userid) throws Exception;
	//로그인
	public MemberDTO selLoginInfo(MemberDTO mDto) throws Exception;
	//회원 정보 수정
	public void update(MemberDTO mDto) throws Exception;
	//회원 탈퇴
	public void delete(MemberDTO mDto) throws Exception;
	//회원 조회
	public MemberDTO selmember(String userid) throws Exception;
}
