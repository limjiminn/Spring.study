package com.springstudy.shop.board.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.springstudy.shop.board.domain.BoardAttachDTO;
import com.springstudy.shop.board.domain.BoardDTO;
import com.springstudy.shop.board.domain.Criteria;
import com.springstudy.shop.board.mapper.BoardAttachMapper;
import com.springstudy.shop.board.mapper.ReplyMapper;
import com.springstudy.shop.board.persistence.IBoardDAO;
import com.springstudy.shop.board.service.IBoardService;

@Service
public class BoardServiceImpl implements IBoardService{
	
	@Autowired 
	private IBoardDAO bDao;
	
	//매퍼 등록
	@Autowired
	private BoardAttachMapper attachMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Transactional
	@Override
	public void register(BoardDTO bDto) throws Exception {
		bDao.create(bDto);
		
		//첨부파일등록 만약 첨부파일이없을때
		if (bDto.getAttachList() == null || bDto.getAttachList().size() <=0) {
			
			return;
		}
		
		bDto.getAttachList().forEach(attch -> {
			attch.setBno(bDto.getBno());
			attachMapper.insert(attch);
		});
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardDTO read(Integer bno) throws Exception {
		bDao.updateViewCnt(bno);
		return bDao.read(bno);
	}
	
	@Transactional
	@Override
	public boolean modify(BoardDTO bDto) throws Exception {
		attachMapper.deleteAll(bDto.getBno());
		boolean modifyResult = bDao.update(bDto) == 1;
		
		if (modifyResult && bDto.getAttachList().size() > 0) {
			
			bDto.getAttachList().forEach(attach -> {
				attach.setBno(bDto.getBno());
				attachMapper.insert(attach);
			});
		}
		return modifyResult;
	}
	
	//게시물 삭제
	@Override 
	public boolean remove(Integer bno) throws Exception {
		//댓글 삭제하고
		replyMapper.deleteAll(bno);
		//첨부파일 삭제하고
		attachMapper.deleteAll(bno);
		//그다음 부모 삭제
		return bDao.delete(bno) == 1;
	}

	@Override
	public List<BoardDTO> listAll(Criteria cri) throws Exception {
		return bDao.listAll(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return bDao.getTotalCnt(cri);
	}

	@Override
	public List<BoardAttachDTO> getAttachList(int bno) {
		
		return attachMapper.findByBno(bno);
	}

}
