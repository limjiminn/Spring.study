package shop.awesomejump.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.awesomejump.domain.MemberOrderDetailDTO;
import shop.awesomejump.domain.OrderDetailDTO;
import shop.awesomejump.domain.OrderInfoDTO;
import shop.awesomejump.mapper.OrderMapper;
import shop.awesomejump.service.IOrderDAO;

@Service
@RequiredArgsConstructor
public class OrderDAOImpl implements IOrderDAO {
	
	private final OrderMapper orderMapper;
	
	
	@Override
	public List<OrderInfoDTO> selectOrderInfoList(int mem_no) {
		return orderMapper.selectOrderInfoList(mem_no);
	}

	@Override
	public List<MemberOrderDetailDTO> selectMemberOrderDetailList(String order_info_uid) {
		return orderMapper.selectMemberOrderDetailList(order_info_uid);
	}

	@Override
	public boolean updateReviewStatus(String order_detail_review_yn, int order_detail_no) {
		return orderMapper.updateReviewStatus(order_detail_review_yn, order_detail_no) == 1;
	}
	
//	@Override
//	public void insertOrder(OrderInfoDTO oiDTO) {
//		 orderMapper.insertOrder(oiDTO);
//	}

	@Override
	public boolean registerOrder(OrderInfoDTO order) {
		return orderMapper.insertOrder(order) == 1;
	}

	@Override
	public OrderInfoDTO getOrderByUid(String order_info_uid) {
		return orderMapper.selectOrderByUid(order_info_uid);
	}

	@Override
	public List<OrderInfoDTO> getOrderByCustNo(int mem_no) {
		return orderMapper.selectOrderByCustNo(mem_no);
	}

	@Override
	public void insertOrderDetail(MemberOrderDetailDTO modDto) {
		 orderMapper.insertOrderDetail(modDto);
	}

	@Override
	public List<MemberOrderDetailDTO> selectOrderDetailByUid(String order_info_uid) {
		
		return orderMapper.selectOrderDetailByUid(order_info_uid);
	}

	

	

	
	
}