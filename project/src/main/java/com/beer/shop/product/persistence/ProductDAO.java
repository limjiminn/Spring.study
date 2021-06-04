package com.beer.shop.product.persistence;

import java.util.List;

import com.beer.shop.product.domain.ProductDTO;

public interface ProductDAO {
	
	//상품 목록
	public List<ProductDTO> productlist() throws Exception;
	
	public ProductDTO productdetail(String pid) throws Exception;

}
