package shop.awesomejump.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class OrderInfoDTO {

	private String order_info_uid;			//주문고유아이디
	private int mem_no;						//회원번호
	private String order_info_imp_uid;		//결제연동사 고유 아이디
	private String order_info_name;			//주문명
	private Date order_info_date;			//주문날짜
	private int order_info_price;			//결제금액
	private int order_info_approval;		//카드 승인번호
	private String order_info_mem_name; 	//구매자 이름
	private String order_info_mem_phone;	//구매자 연락처 
	private String order_info_receive_name; //수령인 이름
	private String order_info_phone;		//배송연락처
	private int order_info_postcode;		//배송지 우편번호
	private String order_info_address;		//배송지주소
	private String order_info_request;		//주문요청사항
	
	private List<MemberOrderDetailDTO> orderDetailList;
}
