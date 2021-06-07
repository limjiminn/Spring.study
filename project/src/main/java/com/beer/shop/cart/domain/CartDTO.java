package com.beer.shop.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private int cartid;		//장바구니 번호
	private String userid;	//사용자 아이디
	private String username;//사용자 이름
	private String pid;		//상품 번호
	private String pname;   //상품 이름
	private int pprice;		//상품 단가
	private int amount;		//구매 수량
	private int money;		//구매 가격
}
