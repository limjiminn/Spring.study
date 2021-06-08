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
	public List<CartDTO> cartMoney() throws Exception {
		return null;
	}
	@Override
	public void insert(CartDTO cDto) throws Exception {
		cDao.insert(cDto);
	}
	@Override
	public List<CartDTO> listCart(String userid) throws Exception {
		System.out.println("service listCart :"+userid);
		return cDao.listCart(userid);
	}
	@Override
	public void delete(int cartid) throws Exception {
		cDao.delete(cartid);
	}
	@Override
	public void deleteAll(String userid) throws Exception {
		cDao.deleteAll(userid);
		
	}
	@Override
	public int sumMoney(String userid) throws Exception {
		return cDao.sumMoney(userid);
	}
	@Override
	public int countCart(String userid, int pid) throws Exception {
		return 0;
	}
	@Override
	public void updateCart(CartDTO cDto) throws Exception {
		
	}
	@Override
	public void modifyCart(CartDTO cDto) throws Exception {
		cDao.modifyCart(cDto);
	}


	
}
