package com.beer.shop.cart.service;

import java.util.List;

import com.beer.shop.cart.domain.CartDTO;

public interface CartService {
	
	public List<CartDTO> cartMoney() throws Exception;
	
	public void insert(CartDTO cDto)throws Exception;
	
	public List<CartDTO> listCart(String userid)throws Exception;
	//장바구니 개별삭제
	public void delete(int cartid) throws Exception;
	//장바구니 비우기
	public void deleteAll(String userid) throws Exception;
	//장바구니 금액 합계
	public int sumMoney(String userid) throws Exception;
	//장바구니 상품 갯수
	public int countCart(String userid, int pid) throws Exception;
	//장바구니 수정
	public void updateCart(CartDTO cDto) throws Exception;
	public void modifyCart(CartDTO cDto) throws Exception;
}
