package com.springstudy.shop.board.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.shop.board.domain.Criteria;
import com.springstudy.shop.board.domain.ReplyDTO;
import com.springstudy.shop.board.mapper.ReplyMapper;
import com.springstudy.shop.board.persistence.IBoardDAO;
import com.springstudy.shop.board.service.IReplyService;
@Service
public class IReplyServiceImpl implements IReplyService{
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private IBoardDAO boardDao;
	
	private static final Logger log = LoggerFactory.getLogger(IReplyServiceImpl.class);
	
	@Override
	public int register(ReplyDTO replyDto) {
		log.info("Reply register..........." + replyDto);
		return mapper.insert(replyDto);
	}

	@Override
	public ReplyDTO read(int rno) {
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyDTO replyDto) {
		return mapper.update(replyDto);
	}

	@Override
	public int remove(int rno) {
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyDTO> getList(Criteria cri, int bno) {
		return mapper.getListWithPaging(cri, bno);
	}

}
