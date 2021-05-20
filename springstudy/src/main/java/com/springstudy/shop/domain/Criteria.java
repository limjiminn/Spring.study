package com.springstudy.shop.domain;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum;
	private int amount;
	
	public Criteria() {
		//한페이지에 10개씩 보여주겟다
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
