package com.springstudy.shop.persistence.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.shop.domain.BoardDTO;
import com.springstudy.shop.persistence.IBoardDAO;

@Repository
public class BoardDAOImpl implements IBoardDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void create(BoardDTO bDto) throws Exception {
		sqlSession.insert("BoardMapper.create", bDto);
	}

	@Override
	public BoardDTO read(Integer bno) throws Exception {
		return sqlSession.selectOne("BoardMapper.read", bno);
	}

	@Override
	public int update(BoardDTO bDto) throws Exception {
		return sqlSession.update("BoardMapper.update", bDto);
	}

	@Override
	public int delete(Integer bno) throws Exception {
		return sqlSession.delete("BoardMapper.delete", bno);
	}

	@Override
	public List<BoardDTO> listAll() throws Exception {
		return sqlSession.selectList("BoardMapper.listAll");
	}
}
