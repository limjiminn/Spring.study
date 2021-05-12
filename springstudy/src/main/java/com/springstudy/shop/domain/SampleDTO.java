package com.springstudy.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //겟 셋 투스트링 
@AllArgsConstructor
@NoArgsConstructor  //매개변수를 사용핳때
public class SampleDTO {

	private String superName;
	private int age;
	
}
