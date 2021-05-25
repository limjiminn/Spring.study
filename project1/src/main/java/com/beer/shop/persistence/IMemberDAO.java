package com.beer.shop.persistence;

import com.beer.shop.member.domain.MemberDTO;

public interface IMemberDAO {
	
	public String getTime();
	
	public void insertMember(MemberDTO mDto);
	
	public MemberDTO selMember(String userid) throws Exception;
	
	public MemberDTO selLoginInfo(String userid, String userpw) throws Exception;
	
	public int update(MemberDTO mDto) throws Exception;
	
	public int delete(String userid) throws Exception;
} 
