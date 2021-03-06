package shop.awesomejump.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PointListDTO {
	
	private String order_info_name;
	private int order_info_price;
	private int point_amount;
	private String point_au;
	private Date point_date;
	
	//결제페이지
	private int mem_no;
	private String order_info_uid;
}
