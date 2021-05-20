package com.springstudy.shop.domain;

import lombok.Data;

@Data
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		//ceil : 올림
		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		
		this.startPage = this.endPage -9;
		
		int realEnd = (int) (Math.ceil((total * 1.0)/ cri.getAmount()));
		
		if (realEnd <= this.endPage) {
			this.endPage = realEnd;
		}
		//교재 참고...
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
