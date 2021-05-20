package com.springstudy.shop.persistence.Impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springstudy.shop.domain.BoardDTO;
import com.springstudy.shop.domain.Criteria;
import com.springstudy.shop.persistence.IBoardDAO;

@Repository  //구현체라는걸 알리기위해 사용
public class BoardDAOImpl implements IBoardDAO{
	
	@Autowired  //sqlSession 사용을 위해 사용 / 필요한 의존 객체의 타입에 해당하는 Bean을 찾아 주입
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
	public List<BoardDTO> listAll(Criteria cri) throws Exception {
		return sqlSession.selectList("BoardMapper.getListWithPaging", cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return sqlSession.selectOne("BoardMapper.getTotalCnt", cri);
	}
	
	
}
