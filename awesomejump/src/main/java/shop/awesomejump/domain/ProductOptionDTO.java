package shop.awesomejump.domain;

import lombok.Data;

@Data
public class ProductOptionDTO {
	private int product_option_no;
	private int product_no;
	private String product_option_color;
	private String product_option_size;
	private String product_option_texture;
	
}
