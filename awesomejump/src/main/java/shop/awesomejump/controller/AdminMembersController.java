package shop.awesomejump.controller;

// TODO: 회원 목록 페이지 반응형으로 교체

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.domain.paging.MembersCriteria;
import shop.awesomejump.domain.paging.MembersPageDTO;
import shop.awesomejump.service.IAdminMembersDAO;
import shop.awesomejump.util.FileUtils;

@Slf4j
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class AdminMembersController {
	
	private final IAdminMembersDAO adminMembersService;
	
	@GetMapping
	public String members(Model model, @ModelAttribute MembersCriteria criteria) {
		
		int memberListCount = adminMembersService.selectMemberListCount(criteria);
		MembersPageDTO pageParam = new MembersPageDTO(criteria, memberListCount);
		
		log.debug("{}", pageParam);
		List<MemberDTO> memberList = adminMembersService.selectMemberList(pageParam);
		
		model.addAttribute("pageParam", pageParam);
		model.addAttribute("memberList", memberList);
		return "admin/members";
	}
	
	@RequestMapping("/mem_detail")
	public String mem_detail(@RequestParam("mem_no") int mem_no, Model model) throws Exception {
		MemberDTO mDto = adminMembersService.selMember(mem_no);
		mDto = new FileUtils().setMemberProfileImagePath(mDto);
		model.addAttribute("member", mDto);
		
		return "admin/memberDetail";
	}
}
