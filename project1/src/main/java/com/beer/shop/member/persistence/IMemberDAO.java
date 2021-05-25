package com.beer.shop.member.persistence;

import com.beer.shop.member.domain.MemberDTO;

public interface IMemberDAO {
	//생성
	public void create(MemberDTO mDto) throws Exception;
	//조회
	public MemberDTO read(Integer bno) throws Exception;
	//수정
	public int update(MemberDTO mDto) throws Exception;
	//삭제
	public int delete(Integer bno) throws Exception;
}
