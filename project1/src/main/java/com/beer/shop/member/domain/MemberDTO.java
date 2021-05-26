package com.beer.shop.member.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String userid;  //아이디
	private String userpw;  //비밀번호
	private String username; //이름
	private String email;    //이메일
	private Timestamp regdate;  
	private Timestamp updatedate;
}
