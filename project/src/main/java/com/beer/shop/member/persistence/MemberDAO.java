package com.beer.shop.member.persistence;

import com.beer.shop.member.domain.MemberDTO;

public interface MemberDAO {

	//회원가입
	public void insertMember(MemberDTO mDto) throws Exception;
	
	public String getTime(); // 원래 abstract final ?
	
	public MemberDTO selMember(String userid) throws Exception;
	
	public MemberDTO selLoginInfo(String userid, String userpw) throws Exception;
	
	public int update(MemberDTO mDto) throws Exception;
	
	public int delete(String userid) throws Exception;
}
