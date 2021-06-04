package com.beer.shop.product.service;

import java.util.List;

import com.beer.shop.product.domain.ProductDTO;

public interface ProductService {
	
	//상품목록
	public List<ProductDTO> productlist(String pcategory) throws Exception;
	//상품 상세
	public ProductDTO productdetail(String pid) throws Exception;
}
