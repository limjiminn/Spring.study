package com.beer.shop.service;

import java.util.List;

import com.beer.shop.domain.BoardDTO;
import com.beer.shop.domain.Criteria;

public interface IBoardService {
	
	//비즈니스 계층: 고객의 요구사항을 반영하는 계층
	//(프리젠테이션계층과 영속계층의 중간다리 역할)
	public void register(BoardDTO bDto) throws Exception;
	public BoardDTO read(Integer bno) throws Exception;
	public boolean modify(BoardDTO bDto) throws Exception;
	public boolean remove(Integer bno) throws Exception;
	public List<BoardDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;

}
