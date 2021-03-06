package com.beer.shop.board.service;

import java.util.List;

import com.beer.shop.board.domain.Criteria;
import com.beer.shop.board.domain.ReplyDTO;
import com.beer.shop.board.domain.ReplyPageDTO;

public interface IReplyService {
	public int register(ReplyDTO replyDto);
	public ReplyDTO read(int rno);
	public int modify(ReplyDTO replyDto);
	public int remove(int rno);
	public List<ReplyDTO> getList(Criteria cri, int bno);
	//페이지관련기능
	public ReplyPageDTO getListPage(Criteria cri, int bno);
}
