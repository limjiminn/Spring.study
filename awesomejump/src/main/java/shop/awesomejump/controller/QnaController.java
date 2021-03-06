package shop.awesomejump.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import shop.awesomejump.domain.Paging;
import shop.awesomejump.domain.QnaDTO;
import shop.awesomejump.domain.QnaPageDTO;
import shop.awesomejump.service.IQnaDAO;

@Controller
@RequestMapping("/awesomejump/qna")
@AllArgsConstructor
public class QnaController {

	private static final Logger log = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	private IQnaDAO qDao;
	
	@GetMapping("/list")
	public void list(QnaDTO qDto, Paging page, Model model) throws Exception {
		
		log.info("list");
		
		int total = qDao.getTotalCnt(page); // 전체 글 수
		log.info("total = " + total);
		log.info("List : "+qDao.getList(page)); 
		model.addAttribute("list", qDao.getList(page));
		
		model.addAttribute("pageMaker", new QnaPageDTO(page, total));
	}
	
	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String register(QnaDTO qDto, RedirectAttributes rttr) {
		
		System.out.println("register POST : " + qDto);
		
		log.info("register : " + qDto);
		
		int result = 0;
		
		result = qDao.register(qDto);
		
		if (result > 0) {
			System.out.println("등록성공");
		}
		
		rttr.addFlashAttribute("result", qDto.getQna_no());
		
		return "redirect:list";
	}
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void register(QnaDTO qDto, Model model) {
		
		log.info("register GET : " + qDto);
		model.addAttribute(qDto);
	}
	
	@GetMapping({"/read", "/modify"})
	public void get(@RequestParam("qna_no") int qna_no, Model model) {
		
		log.info("/modify");
		model.addAttribute("qDto", qDao.get(qna_no));
		
	}
	
	@GetMapping("/passwordCheck")
	public void getPassword(@RequestParam("qna_no") int qna_no, Model model) {
		log.info("비밀번호 GET" + qna_no);
		
		model.addAttribute("qna_no",qna_no);
	}
	
	@ResponseBody
	@PostMapping({"/read", "/passwordCheck"})
	public int getPassword(QnaDTO qDto) throws Exception {
		log.info("비밀번호 POST");
		log.info("글번호 : " + qDto.getQna_no());
		log.info("비밀번호 : " + qDto.getQna_pw());
		int result = qDao.qnaPwCheck(qDto);
		log.info("result ===== " + result);
		return result;
	}
	
	@PostMapping("/modify")
	public String modify(QnaDTO qDto, Paging page, RedirectAttributes rttr) {
		
		log.info("modify : " + qDto);
		
		if (qDao.modify(qDto)) {
			rttr.addFlashAttribute("result2", "수정되었습니다.");
		}
		
//		rttr.addAttribute("pageNum", page.getPageNum());
//		rttr.addAttribute("amount", page.getAmount());
//		rttr.addAttribute("type", page.getType());
//		rttr.addAttribute("keyword", page.getKeyword());
		
		return "redirect:list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("qna_no") int qna_no, Paging page, RedirectAttributes rttr) {
		
		log.info("remove : " + qna_no);
		
		if (qDao.remove(qna_no)) {
			rttr.addFlashAttribute("result3", "삭제되었습니다.");
		}
		
//		rttr.addAttribute("pageNum", page.getPageNum());
//		rttr.addAttribute("amount", page.getAmount());
//		rttr.addAttribute("type", page.getType());
//		rttr.addAttribute("keyword", page.getKeyword());
		
		return "redirect:list";
	}
	
	
	@PostMapping("/reply")
	@PreAuthorize("hasRole('admin')")
	public String registerReply(@RequestParam("qna_no") int qna_no, QnaDTO qDto, Model rttr) {

		log.info("/reply....." + qDto);
		
		// rttr.addAttribute("qDto", qDao.get(qna_no));
		
		qDao.registerReply(qDto);
		
		rttr.addAttribute("qDto", qDto.getQna_no());
		rttr.addAttribute("qDto",qDto.getQna_ref());
		rttr.addAttribute("qDto",qDto.getQna_restep());
		rttr.addAttribute("qDto",qDto.getQna_relevel());
		rttr.addAttribute("qDto",qDto.getQna_reply_yn());
		
		return "redirect:list";
	}
	
	@GetMapping("/reply")
	@PreAuthorize("hasRole('admin')")
	public void registerReply(QnaDTO qDto, Model model) {
		log.info("qna_no : " + qDto.getQna_no());
		log.info("ref : " + qDto.getQna_ref());
		log.info("restep : " + qDto.getQna_restep());
		log.info("relevel : " + qDto.getQna_relevel());
		
		model.addAttribute("qDto", qDto);
	}
	
	@GetMapping({"/readReply", "/modifyReply"})
	public void getRead(@RequestParam("qna_ref")int qna_ref, Model model) {
		
		log.info("/readReply or /modifyReply");
		
		//log.info("qna_no : " + qna_no);
		log.info("qna_ref : " + qna_ref);
		log.info("qDao : " + qDao.getReply( qna_ref));
		
		model.addAttribute("qDto", qDao.getReply(qna_ref));
	}
	
//	@PostMapping("/modifyReply")
//	public String modifyReply(QnaDTO qDto, RedirectAttributes rttr) {
//		
//		log.info("/modifyReply ....." + qDto);
//		
//		if (qDao.modifyReply(qDto)) {
//			rttr.addFlashAttribute("result2", "수정되었습니다.");
//		}
//		
//		return "redirect:list";
//	}
	
	@PostMapping("/removeReply")
	public String removeReply(@RequestParam("qna_no") int qna_no, RedirectAttributes rttr) {
		
		log.info("removeReply : " + qna_no);
		
		if (qDao.removeReply(qna_no)) {
			rttr.addFlashAttribute("result3", "삭제되었습니다.");
		}
		
		return "redirect:list";
	}
	
}
