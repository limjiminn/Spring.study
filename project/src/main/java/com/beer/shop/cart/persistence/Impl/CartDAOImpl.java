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
	
	
	@Override
	public List<CartDTO> cartMoney() throws Exception {

		return null;
	}
	//장바구니 추가
	@Override
	public void insert(CartDTO cDto) throws Exception {
		session.insert("cartMapper.insert",cDto);
		
	}
	//장바구니 목록
	@Override
	public List<CartDTO> listCart(String userid) {
		System.out.println("dao listCart :"+userid);
		return session.selectList("cartMapper.listCart", userid);
	}
	@Override
	public void delete(int cartid) throws Exception {
		session.delete("cartMapper.delete", cartid);
		
	}
	@Override
	public void deleteAll(String userid) throws Exception {
		session.delete("cartMapper.deleteAll",userid);
		
	}
	@Override
	public int sumMoney(String userid) throws Exception {
		
		return session.selectOne("cartMapper.sumMoney",userid);
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
		session.update("cartMapper.modify",cDto);
	}


}
