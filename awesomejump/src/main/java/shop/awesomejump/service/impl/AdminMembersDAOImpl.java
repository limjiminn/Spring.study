package shop.awesomejump.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.domain.paging.MembersCriteria;
import shop.awesomejump.domain.paging.MembersPageDTO;
import shop.awesomejump.mapper.AdminMembersMapper;
import shop.awesomejump.service.IAdminMembersDAO;

@Service
@RequiredArgsConstructor
public class AdminMembersDAOImpl implements IAdminMembersDAO {

	private final AdminMembersMapper adminMembersMapper;

	@Override
	public List<MemberDTO> selectMemberList(MembersPageDTO pageParam) {
		return adminMembersMapper.selectMemberList(pageParam);
	}

	@Override
	public int selectMemberListCount(MembersCriteria criteria) {
		return adminMembersMapper.selectMemberListCount(criteria);
	}

	@Override
	public MemberDTO selMember(int mem_no) {
		return adminMembersMapper.selectMember(mem_no);
	}
	
}