package shop.awesomejump.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "awesomejump/index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "awesomejump/login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "awesomejump/register";
	}
	
	@GetMapping("/category")
	public String category() {
		return "awesomejump/category";
	}
	
	@GetMapping("/checkout")
	public String checkout() {
		return "awesomejump/checkout";
	}
	
	@GetMapping("/confirmation")
	public String confirmation() {
		return "awesomejump/confirmation";
	}
	
	@GetMapping("/tracking-order")
	public String trackingOrder() {
		return "awesomejump/tracking-order";
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
	
	@GetMapping("/cart")
	public String cart() {
		return "awesomejump/cart";
	}


}
