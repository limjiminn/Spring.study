package com.beer.shop.board.service;

import java.util.List;

import com.beer.shop.board.domain.BoardAttachDTO;
import com.beer.shop.board.domain.BoardDTO;
import com.beer.shop.board.domain.Criteria;

public interface IBoardService {
	//컨트롤러와 persistence의 다리역할 = 서비스 계층
	//비즈니스 계층
	//게시판 작성
	public void register(BoardDTO bDto) throws Exception;
	//게시판 보기
	public BoardDTO read(Integer bno) throws Exception;
	//게시판 수정
	public boolean modify(BoardDTO bDto) throws Exception;
	//게시판 삭제
	public boolean remove(Integer bno) throws Exception;
	//게시판 목록
	public List<BoardDTO> listAll(Criteria cri) throws Exception;
	//게시판 전체갯수
	public int getTotalCnt(Criteria cri) throws Exception;
	
	/* public List<BoardAttachDTO> getAttachList(int bno); */
}
