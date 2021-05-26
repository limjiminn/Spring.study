package com.springstudy.shop.board.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springstudy.shop.board.mapper.Sample1Mapper;
import com.springstudy.shop.board.service.ISampleTxService;

@Service
public class ISampleTxServiceImpl implements ISampleTxService{
	
	@Autowired
	private Sample1Mapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(ISampleTxServiceImpl.class);
	
	@Transactional
	@Override
	public void addDate(String value) {
		log.info("insertCol1............");
		mapper.insertCol1(value);
		
		log.info("inserCol2.............");
		mapper.insertCol2(value);
		
		log.info("end..................");
		
	}

}
