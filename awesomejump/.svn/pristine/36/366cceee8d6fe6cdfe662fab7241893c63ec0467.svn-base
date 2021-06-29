package shop.awesomejump.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.awesomejump.mapper.HomeMapper;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {
	
	private final HomeMapper mapper;
	
	@GetMapping
	public String home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String getTime = mapper.getTime();
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("getTime", getTime);
		
		return "home";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/";
	}
}
