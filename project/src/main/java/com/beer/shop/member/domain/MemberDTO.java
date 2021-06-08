package com.beer.shop.member.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 도메인 : 하나의 온전한 시스템 단위 예) 회원, 상품, 배송
 * 개발자에게 도메인 클래스는 특정 테이블과 유사한 속성을 가지는 클래스를 의미
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String userid; //회원아이디
	private String userpw; //회원비밀번호
	private String username; //회원이름
	private String email;	//이메일
	private Timestamp regdate;
	private Timestamp updatedate;

}
