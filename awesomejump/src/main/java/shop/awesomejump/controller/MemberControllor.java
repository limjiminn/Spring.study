package shop.awesomejump.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberControllor {

	@PostMapping("/register")
	public String register() {
		return "";
	}
}
