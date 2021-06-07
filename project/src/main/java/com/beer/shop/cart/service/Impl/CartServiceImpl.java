package com.beer.shop.cart.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer.shop.cart.domain.CartDTO;
import com.beer.shop.cart.persistence.CartDAO;
import com.beer.shop.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartDAO cDao;
	@Override
	public void insert(CartDTO cDto) throws Exception {
		cDao.insert(cDto);
	}
	@Override
	public List<CartDTO> listCart() throws Exception {
		return null;
	}


	
}
