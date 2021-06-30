package shop.awesomejump.domain;

import lombok.Data;

@Data
public class OrderDetailDTO {

	private int order_derail_no;		//주문상세시퀀스
	private String order_info_uid;		//주문고유아이디
	private int product_no;				//상품고유번호
	private int order_detail_quantity;	//상품수량
	private String order_detail_shipping;//배송진행상황
}
