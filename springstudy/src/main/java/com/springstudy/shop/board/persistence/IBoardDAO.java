package com.springstudy.shop.board.persistence;

import java.util.List;

import com.springstudy.shop.board.domain.BoardDTO;
import com.springstudy.shop.board.domain.Criteria;

public interface IBoardDAO {
	
	public void create(BoardDTO bDto) throws Exception;
	public BoardDTO read(Integer bno) throws Exception;
	public int update(BoardDTO bDto) throws Exception;
	public int delete(Integer bno) throws Exception;
	public List<BoardDTO> listAll(Criteria cri) throws Exception;
	
	public int getTotalCnt(Criteria cri) throws Exception;
	
	public void updateViewCnt(Integer bno) throws Exception;
	
	public void updateReplyCnt(Integer bno, int amount) throws Exception;
	
}
