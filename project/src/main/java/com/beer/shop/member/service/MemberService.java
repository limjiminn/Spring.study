package com.beer.shop.member.service;

import com.beer.shop.member.domain.MemberDTO;

public interface MemberService {

	public void insertMember(MemberDTO mDto) throws Exception;
	
	public MemberDTO selMember(String userid) throws Exception;
	
	public MemberDTO selLoginInfo(String userid, String userpw) throws Exception;
	
	public boolean update(MemberDTO mDto) throws Exception;
	
	public boolean delete(String userid) throws Exception;
}
