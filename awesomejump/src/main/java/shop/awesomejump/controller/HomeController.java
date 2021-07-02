package shop.awesomejump.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "awesomejump/index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "awesomejump/register";
	}
	
	@GetMapping("/login")
	public String login() {
		return "awesomejump/login";
	}
	
	// LOGOUT
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/category")
	public String category() {
		return "awesomejump/category";
	}
	
	@GetMapping("/cart")
	public String cart() {
		return "awesomejump/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout() {
		return "awesomejump/checkout";
	}
	
	@GetMapping("/confirmation")
	public String confirmation() {
		return "awesomejump/confirmation";
	}
	
	@GetMapping("/tracking")
	public String trackingOrder() {
		return "awesomejump/tracking";
	}
	
	@GetMapping("/blog")
	public String blog() {
		return "awesomejump/blog";
	}
	
	@GetMapping("/single-blog")
	public String singleBlog() {
		return "awesomejump/single-blog";
	}
	
	@GetMapping("/single-product")
	public String singleProduct() {
		return "awesomejump/single-product";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "awesomejump/contact";
	}

	@GetMapping("/elements")
	public String elements() {
		return "awesomejump/elements";
	}
	
	@GetMapping("/feature")
	public String feature() {
		return "awesomejump/feature";
	}
}
