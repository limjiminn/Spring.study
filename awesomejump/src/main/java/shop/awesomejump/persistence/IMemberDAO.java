package shop.awesomejump.persistence;

import shop.awesomejump.domain.MemberDTO;

public interface IMemberDAO {
	
	public int insertMember(MemberDTO mDto);
	public int confirmEmail(String userEmail) throws Exception;
	public int confirmNickname(String userNickname) throws Exception;
	
}
