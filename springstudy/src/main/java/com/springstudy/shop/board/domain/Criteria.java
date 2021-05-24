package com.springstudy.shop.board.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Data
public class Criteria {
	private int pageNum;
	private int amount;
	
	//검색어
	private String type;
	private String keyword;
	
	
	public Criteria() {
		//한페이지에 10개씩 보여주겟다
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//타이틀 컨텐츠 저자 
	public String[] getTypeArr() {
		
		return type == null? new String[]{} : type.split("");
	}
	
	//UriComponentsBuilder는 여러개의 파라미터들을 연결해서 URL의 형태를 만들어주는 기능을 가지고있다.
	
//	public String getListLink() {
//		
//		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
//				.queryParam("pageNum", this.pageNum)
//				.queryParam("amount", this.getAmount())
//				.queryParam("type", this.getType())
//				.queryParam("keyword", this.getKeyword());
//		return builder.toString();
//	}
}
