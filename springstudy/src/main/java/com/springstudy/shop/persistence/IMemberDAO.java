package com.springstudy.shop.persistence;

import com.springstudy.shop.member.domain.MemberDTO;

public interface IMemberDAO {
	
	public String getTime(); // 원래 abstract final ?
	
	public void insertMember(MemberDTO mDto); // 매개변수는 외부로부터 뭔가를 넘겨받을 수 있는 유일한 방법
	
	public MemberDTO selMember(String userid) throws Exception;
	
	public MemberDTO selLoginInfo(String userid, String userpw) throws Exception;
	
	public int update(MemberDTO mDto) throws Exception;
	
	public int delete(String userid) throws Exception;
}
