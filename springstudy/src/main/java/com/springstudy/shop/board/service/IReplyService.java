package com.springstudy.shop.board.service;

import java.util.List;

import com.springstudy.shop.board.domain.Criteria;
import com.springstudy.shop.board.domain.ReplyDTO;

public interface IReplyService {
	public int register(ReplyDTO replyDto);
	public ReplyDTO read(int rno);
	public int modify(ReplyDTO replyDto);
	public int remove(int rno);
	public List<ReplyDTO> getList(Criteria cri, int bno);
}
