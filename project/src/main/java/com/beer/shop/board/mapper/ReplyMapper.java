package com.beer.shop.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.beer.shop.board.domain.Criteria;
import com.beer.shop.board.domain.ReplyDTO;

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
	//댓글 작성후 
	public List<ReplyDTO> getListWithPaging(
					@Param("cri") Criteria cri,
					@Param("bno") int bno);
	//게시물 확인시 Count
	public int getCountByBno(int bno);
	
	//게시물 삭제시 댓글삭제
	public int deleteAll(int bno);
}
