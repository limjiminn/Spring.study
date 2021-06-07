package com.beer.shop.cart.persistence.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beer.shop.cart.domain.CartDTO;
import com.beer.shop.cart.persistence.CartDAO;

@Repository
public class CartDAOImpl implements CartDAO {
	
	@Autowired
	SqlSession session;
	//장바구니 추가
	@Override
	public void insert(CartDTO cDto) throws Exception {
		session.insert("cartMapper.insert",cDto);
		
	}
	//장바구니 목록
	@Override
	public List<CartDTO> listCart(String userid) {
		
		return session.selectList("cartMapper.listCart", userid);
	}


}
