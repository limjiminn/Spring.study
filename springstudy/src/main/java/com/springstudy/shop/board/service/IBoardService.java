package com.springstudy.shop.board.service;

import java.util.List;

import com.springstudy.shop.board.domain.BoardAttachDTO;
import com.springstudy.shop.board.domain.BoardDTO;
import com.springstudy.shop.board.domain.Criteria;

public interface IBoardService {
	//컨트롤러와 persistence의 다리역할 = 서비스 계층
	//비즈니스 계층
	public void register(BoardDTO bDto) throws Exception;
	public BoardDTO read(Integer bno) throws Exception;
	public boolean modify(BoardDTO bDto) throws Exception;
	public boolean remove(Integer bno) throws Exception;
	public List<BoardDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;
	public List<BoardAttachDTO> getAttachList(int bno);
}
