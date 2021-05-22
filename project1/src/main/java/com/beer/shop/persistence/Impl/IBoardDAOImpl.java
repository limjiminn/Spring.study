package com.beer.shop.persistence.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beer.shop.domain.BoardDTO;
import com.beer.shop.domain.Criteria;
import com.beer.shop.persistence.IBoardDAO;

@Repository
public class IBoardDAOImpl implements IBoardDAO{
	
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
		return sqlSession.selectOne("BoardMapper.update", bDto);
	}

	@Override
	public int delete(Integer bno) throws Exception {
		return sqlSession.delete("BoardMapper.delete", bno);
	}

	@Override
	public List<BoardDTO> listAll(Criteria cri) throws Exception {
		return sqlSession.selectList("BoardMapper.getListWithPaging", cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return sqlSession.selectOne("BoardMapper.getTotalCnt", cri);
	}
	

}
