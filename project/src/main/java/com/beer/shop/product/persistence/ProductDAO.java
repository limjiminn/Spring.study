package com.beer.shop.product.persistence;

import java.util.List;

import com.beer.shop.product.domain.ProductDTO;

public interface ProductDAO {
	
	//상품 목록
	public List<ProductDTO> productlist(String pcategory) throws Exception;
	//상품 상세
	public ProductDTO productdetail(String pid) throws Exception;

}
