package com.beer.shop.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private String pid;			//상품아이디
	private String pname;		//상품이름
	private int pprice;			//상품가격
	private String pcategory;	//상품분류
	private String pdescription;//상품내용
	private String pcompany;	//상품회사
	private int pstock;			//상품수량
	private String pimg;		//상품이미지
}
