package com.beer.shop.product.service;

import java.util.List;

import com.beer.shop.product.domain.ProductDTO;

public interface ProductService {
	
	//상품목록
	public List<ProductDTO> productlist() throws Exception;
	
	public ProductDTO productdetail(String pid) throws Exception;
}
