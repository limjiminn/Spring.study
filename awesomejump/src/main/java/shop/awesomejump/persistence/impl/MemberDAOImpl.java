package shop.awesomejump.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.mapper.MemberMapper;
import shop.awesomejump.persistence.IMemberDAO;

@Repository
public class MemberDAOImpl implements IMemberDAO{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public int insertMember(MemberDTO mDto) {
		
		int result = 0;
		result = memberMapper.insertMember(mDto);
		
		return result;
	}
}
