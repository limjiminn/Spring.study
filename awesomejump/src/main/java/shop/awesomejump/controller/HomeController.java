package shop.awesomejump.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

	@Autowired
	String uploadPath;
	
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
	
	@GetMapping("/test")
	public String test() {
		return "awesomejump/test";
	}
	
	@PostMapping("/testUpload")
	public String testUpload(MultipartFile file, Model model) {
		
		String result = file.getOriginalFilename();
		
		File upload = new File(uploadPath, result);
		
		try {
			file.transferTo(upload);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("result", result);
		return "awesomejump/test";
	}

}
