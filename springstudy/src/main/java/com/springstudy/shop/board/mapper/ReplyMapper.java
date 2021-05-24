package com.springstudy.shop.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springstudy.shop.board.domain.Criteria;
import com.springstudy.shop.board.domain.ReplyDTO;

public interface ReplyMapper {
	
	//추상메서드를 만든다.
	public String getTime2();
	//등록
	public int insert(ReplyDTO replyDTO);
	//조회
	public ReplyDTO read(int rno);
	//삭제
	public int delete(int rno);
	//수정
	public int update(ReplyDTO reply);
	
	public List<ReplyDTO> getListWithPaging(
					@Param("cri") Criteria cri,
					@Param("bno") int bno);
}
