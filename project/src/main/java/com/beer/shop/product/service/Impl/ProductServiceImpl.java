package com.beer.shop.product.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer.shop.product.domain.ProductDTO;
import com.beer.shop.product.persistence.ProductDAO;
import com.beer.shop.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO pDao;
	
	@Override
	public List<ProductDTO> productlist() throws Exception {
		System.out.println("service확인");
		return pDao.productlist();
	}

	@Override
	public ProductDTO productdetail(String pid) throws Exception {
		
		return pDao.productdetail(pid);
	}

}
