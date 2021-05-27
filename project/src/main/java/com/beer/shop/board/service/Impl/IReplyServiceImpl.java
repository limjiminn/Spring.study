package com.beer.shop.board.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beer.shop.board.domain.Criteria;
import com.beer.shop.board.domain.ReplyDTO;
import com.beer.shop.board.domain.ReplyPageDTO;
import com.beer.shop.board.mapper.ReplyMapper;
import com.beer.shop.board.persistence.IBoardDAO;
import com.beer.shop.board.service.IReplyService;
@Service
public class IReplyServiceImpl implements IReplyService{
	//스프링 4.3을 이용하는경우에만 사용(ReplyMapper)
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private IBoardDAO boardDao;
	
	private static final Logger log = LoggerFactory.getLogger(IReplyServiceImpl.class);
	
	@Transactional
	@Override
	public int register(ReplyDTO replyDto) {
		log.info("Reply register..........." + replyDto);
		try {
			boardDao.updateReplyCnt(replyDto.getBno(), +1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapper.insert(replyDto);
	}

	@Override
	public ReplyDTO read(int rno) {
		log.info("Reply read........" + rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyDTO replyDto) {
		log.info("Reply modify......" + replyDto);
		return mapper.update(replyDto);
	}
	@Transactional
	@Override
	public int remove(int rno) {
		ReplyDTO replyDto = mapper.read(rno);
		try {			
			boardDao.updateReplyCnt(replyDto.getBno(), -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Reply remove....." + rno);
		return mapper.delete(rno);
	}

	@Override
	public List<ReplyDTO> getList(Criteria cri, int bno) {
		log.info("Reply getList....." + cri);
		log.info("Reply getList....." + bno);
		
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, int bno) {
		log.info("Reply getListPage......" + cri);
		log.info("Reply getListPage......" + bno);
		
		return new ReplyPageDTO(mapper.getCountByBno(bno),		
								mapper.getListWithPaging(cri, bno));
	}

}
