package com.beer.shop.board.persistence;

import java.util.List;

import com.beer.shop.board.domain.BoardDTO;
import com.beer.shop.board.domain.Criteria;

public interface IBoardDAO {
	
	//생성
	public void create(BoardDTO bDto) throws Exception;
	//상세
	public BoardDTO read(Integer bno) throws Exception;
	//수정
	public int update(BoardDTO bDto) throws Exception;
	//삭제
	public int delete(Integer bno) throws Exception;
	//목록
	public List<BoardDTO> listAll(Criteria cri) throws Exception;
	//총 갯수
	public int getTotalCnt(Criteria cri) throws Exception;
	
	
}
