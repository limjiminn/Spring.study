package com.beer.shop.member.service;

import com.beer.shop.member.domain.MemberDTO;

public interface MemberService {

	public void insertMember(MemberDTO mDto) throws Exception;
	//아이디 중복 검사
	public int selMember(String userid) throws Exception;
	
	public MemberDTO selLoginInfo(MemberDTO mDto) throws Exception;
	
	public boolean update(MemberDTO mDto) throws Exception;
	
	public boolean delete(String userid) throws Exception;
}
