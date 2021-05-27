package com.beer.shop.board.mapper;

import java.util.List;

import com.beer.shop.board.domain.BoardAttachDTO;

public interface BoardAttachMapper {
	
	public void insert(BoardAttachDTO boardAttachDto);
	public void delete(String uuid);
	public List<BoardAttachDTO> findByBno(int bno);
}
