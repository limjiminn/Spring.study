package shop.awesomejump.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.awesomejump.domain.MemberOrderDetailDTO;
import shop.awesomejump.domain.OrderDetailDTO;
import shop.awesomejump.domain.OrderInfoDTO;

public interface OrderMapper {

	public List<OrderInfoDTO> selectOrderInfoList(int mem_no);
	
	public List<MemberOrderDetailDTO> selectMemberOrderDetailList(String order_info_uid);

	public int updateReviewStatus(@Param("order_detail_review_yn") String order_detail_review_yn,
								  @Param("order_detail_no") int order_detail_no);
	
	public int insertOrder(OrderInfoDTO oiDTO);
	
	public void insertOrderDetail(MemberOrderDetailDTO modDto);
	
	public OrderInfoDTO selectOrderByUid(String order_info_uid);
	
	public List<OrderInfoDTO> selectOrderByCustNo(int mem_no);
	
	public List<MemberOrderDetailDTO> selectOrderDetailByUid(String order_info_uid);
}
