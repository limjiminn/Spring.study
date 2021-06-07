package com.beer.shop.cart.persistence;

import java.util.List;

import com.beer.shop.cart.domain.CartDTO;

public interface CartDAO {
	//장바구니 추가
	public void insert(CartDTO cDto) throws Exception;
	//장바구니 목록
	public List<CartDTO> listCart(String userid);
	//장바구니 삭제
	
	//장바구니 수정
	//장바구니 금액 합계
	//장바구니 상품 확인
	//장바구니 상품 수량 변경
}
