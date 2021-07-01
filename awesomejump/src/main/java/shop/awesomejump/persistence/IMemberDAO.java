package shop.awesomejump.persistence;

import shop.awesomejump.domain.MemberDTO;

public interface IMemberDAO {
	
	public int insertMember(MemberDTO mDto);
	
}
