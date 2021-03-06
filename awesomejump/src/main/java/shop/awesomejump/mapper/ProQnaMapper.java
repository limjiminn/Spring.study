package shop.awesomejump.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.awesomejump.domain.Paging;
import shop.awesomejump.domain.ProductQnaDTO;
import shop.awesomejump.domain.QnaDTO;

public interface ProQnaMapper {

	public int insert(ProductQnaDTO pDto); //등록
	
	public List<ProductQnaDTO> getListReply(); //관리자페이지 리스트
	public int insertReply(ProductQnaDTO pDto); //답글 등록
	public int updateReplyYN(ProductQnaDTO pDto); //답글 등록시 yn 업데이트
	public ProductQnaDTO readReply(int pro_qna_no);
	public int deleteReply(int pro_qna_no);
	public int updateReply(ProductQnaDTO pDto);
	
	public ProductQnaDTO read(int pro_qna_no); //읽기
	public int delete (int pro_qna_no); //삭제
	public int update(ProductQnaDTO pDto); //수정
	public List<ProductQnaDTO> getListWithPaging(@Param("page") Paging page, @Param("product_no") int product_no);

	public ProductQnaDTO readReply(ProductQnaDTO pDto);
}
