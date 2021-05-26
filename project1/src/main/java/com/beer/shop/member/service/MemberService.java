package com.beer.shop.member.service;


import org.springframework.stereotype.Service;

import com.beer.shop.member.domain.MemberDTO;

@Service
public interface MemberService {
	
	public void insertMember(MemberDTO mDto) throws Exception ;
	
}
