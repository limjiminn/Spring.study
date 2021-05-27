package com.beer.shop.member.persistence;

import com.beer.shop.member.domain.MemberDTO;

public interface MemberDAO {

	//회원가입
	public void inserMember(MemberDTO mDto) throws Exception;
}
