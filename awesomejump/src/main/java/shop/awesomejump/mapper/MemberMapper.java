package shop.awesomejump.mapper;

import shop.awesomejump.domain.MemberDTO;

public interface MemberMapper {

	public MemberDTO selectMemberByEmail(String mem_email);
	public int insertMember(MemberDTO memBto);
	public MemberDTO confirmEmail(String userEmail);
	public MemberDTO confirmNickname(String userNickname);
}
