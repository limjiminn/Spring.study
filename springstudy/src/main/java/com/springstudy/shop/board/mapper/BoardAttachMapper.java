package com.springstudy.shop.board.mapper;

import java.util.List;

import com.springstudy.shop.board.domain.BoardAttachDTO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachDTO boardAttachDto);
	public void delete(String uuid);
	public List<BoardAttachDTO> findByBno(int bno);
	//삭제
	public void deleteAll(int bno);
}
