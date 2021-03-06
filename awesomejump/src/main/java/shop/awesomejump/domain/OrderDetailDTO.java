package shop.awesomejump.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailDTO {

	private int order_detail_no;		  // 주문상세시퀀스
	private String order_info_uid;		  // 주문고유아이디
	private int product_no;				  // 상품고유번호
	private int order_detail_quantity;	  // 상품수량
	private String order_detail_shipping; // 배송진행상황
	private String product_option_no;     // 상품옵션시퀀스
	private String order_detail_review_yn;
}
