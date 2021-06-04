package com.beer.shop.product.persistence.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beer.shop.product.domain.ProductDTO;
import com.beer.shop.product.persistence.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO{
	
	@Autowired
	private SqlSession session;
	
	
	//상품 조회
	@Override
	public List<ProductDTO> productlist(String pcategory) throws Exception {
		System.out.println("DAO확인");
		return session.selectList("ProductMapper.productlist", pcategory);
	}

	//상품 상세
	@Override
	public ProductDTO productdetail(String pid) throws Exception {
	
		return session.selectOne("ProductMapper.productdetail", pid);
	}

}
