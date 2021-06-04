package com.beer.shop.product;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.beer.shop.product.domain.ProductDTO;
import com.beer.shop.product.service.ProductService;

@Controller
@RequestMapping("/product/")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService service;
	
	//상품 목록
	@RequestMapping(value = "/productlist" , method = RequestMethod.GET)
	public void productlist( Model model) throws Exception{
		log.info("========== productlist ===========");
		
		List<ProductDTO> list = null; 
			list = service.productlist();
		
		log.info("list : " + list);
		model.addAttribute("list" , list);
	}
	@RequestMapping(value = "/productdetail", method = RequestMethod.GET)
	public void productdetail(@RequestParam("pid") String pid, Model model) throws Exception{
		log.info("============productdetail=============");
		ProductDTO result = service.productdetail(pid);
		
		model.addAttribute("product",result);
	}
}
