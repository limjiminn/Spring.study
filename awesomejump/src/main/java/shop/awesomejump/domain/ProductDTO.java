package shop.awesomejump.domain;

import lombok.Data;

@Data
public class ProductDTO {
	private int product_no;
	private int category_no;
	private int product_stock;
	private int product_grade;
	private String product_interest_yn;
	private int product_point;
	private String product_name;
	private String product_review;
	private int product_price;
	private String product_content;
	

}