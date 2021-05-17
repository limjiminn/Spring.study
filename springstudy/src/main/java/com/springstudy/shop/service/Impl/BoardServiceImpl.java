package com.springstudy.shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.shop.domain.BoardDTO;
import com.springstudy.shop.persistence.IBoardDAO;
import com.springstudy.shop.service.IBoardService;

@Service
public class BoardServiceImpl implements IBoardService{
	
	@Autowired 
	private IBoardDAO bDao;
	
	@Override
	public void register(BoardDTO bDto) throws Exception {
		bDao.create(bDto);
	}

	@Override
	public BoardDTO read(Integer bno) throws Exception {
		return bDao.read(bno);
	}

	@Override
	public boolean modify(BoardDTO bDto) throws Exception {
		return bDao.update(bDto) == 1;
	}

	@Override
	public boolean remove(Integer bno) throws Exception {
		return bDao.delete(bno) == 1;
	}

	@Override
	public List<BoardDTO> listAll() throws Exception {
		return bDao.listAll();
	}

}
