package shop.awesomejump.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.awesomejump.domain.ImageVO;
import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.domain.MemberOrderDetailDTO;
import shop.awesomejump.domain.OrderInfoDTO;
import shop.awesomejump.domain.ProImageDTO;
import shop.awesomejump.domain.ReviewDTO;
import shop.awesomejump.domain.ReviewImageDTO;
import shop.awesomejump.service.IOrderDAO;
import shop.awesomejump.service.IProductDAO;
import shop.awesomejump.service.IReviewDAO;
import shop.awesomejump.util.FileUtils;

@Slf4j
@Controller
@Secured("ROLE_USER")
@RequestMapping("/orders")
@RequiredArgsConstructor
public class MemberOrdersController {
	
	private final String uploadPath;
	private final IOrderDAO orderService;
	private final IProductDAO productService;
	private final IReviewDAO reviewService;
	
	@GetMapping
	public String orders(@AuthenticationPrincipal MemberDTO member, Model model) {

		List<OrderInfoDTO> orderInfoList = orderService.selectOrderInfoList(member.getMem_no());
		
		orderInfoList.forEach(orderInfo -> {
			List<MemberOrderDetailDTO> orderDetailList = 
					orderService.selectMemberOrderDetailList(orderInfo.getOrder_info_uid());
			
			orderDetailList.forEach(orderDetail -> {
				
				ProImageDTO productImage = new ProImageDTO();
				try {
					productImage = productService.getImageList(orderDetail.getProduct_no()).get(0);
				} catch (Exception e) { e.printStackTrace();}
				
				String imagePath = productImage.getPro_image_path() + "/s_"
								 + productImage.getPro_image_uuid() + "_"
								 + productImage.getPro_image_name();
				orderDetail.setImagePath(imagePath);
			});
			
			orderInfo.setOrderDetailList(orderDetailList);
		});

		model.addAttribute("orderInfoList", orderInfoList);
		return "awesomejump/orders";
	}
	
	@ResponseBody
	@PostMapping("/review/upload")
	public List<ImageVO> uploadReviewImages(MultipartFile[] uploadedImage) {
		
		FileUtils fileUtils = new FileUtils();
		String currentDatePath = fileUtils.getCurrentDatePath();
		Path imagePath = Paths.get("review", currentDatePath);
		
		List<ImageVO> imageList = new ArrayList<>();
		try {
			imageList = fileUtils.uploadImagesToPath(uploadedImage, uploadPath, imagePath, true);
		} catch (Exception e) { e.printStackTrace(); }
		
		log.debug("{}", imageList);
		return imageList;
	}
	
	@ResponseBody
	@PostMapping("/review/remove")
	public void removeReviewImage(@RequestBody ImageVO image) {
		
		File uploadedFolder = new File(uploadPath + File.separator + image.getImagePath());
		File[] uploadedFileList = uploadedFolder.listFiles();
		for (File file : uploadedFileList) {
			if (file.getName().contains(image.getImageUuid())) {
				file.delete();
			}
		}
	}
	
	@Transactional
	@PostMapping("/review/write")
	public String writeReviewImage(@ModelAttribute ReviewDTO review, @RequestParam String imageArray,
								   @AuthenticationPrincipal MemberDTO member, Model model) {
		
		List<ImageVO> imageList = Collections.emptyList();
		try {
			imageList = new ObjectMapper().readValue(imageArray, new TypeReference<List<ImageVO>>(){});
		} catch (IOException e) { e.printStackTrace(); }

		review.setMem_no(member.getMem_no());
		review.setReview_name(member.getMem_nickname());
		boolean reviewWriteResult = reviewService.insertReview(review);
		
		imageList.forEach(image -> {
			ReviewImageDTO reviewImage = ReviewImageDTO.builder()
					.review_no(review.getReview_no())
					.review_image_path(image.getImagePath())
					.review_image_name(image.getImageUuid() + "_" + image.getImageName())
					.review_image_type(image.getImageType())
					.build();
			reviewService.insertReviewImage(reviewImage);
		});

		orderService.updateReviewStatus("y", review.getOrder_detail_no());
		productService.updateProductReview(review.getProduct_no());
		
		model.addAttribute("reviewWriteResult", reviewWriteResult);
		return "redirect:/orders";
	}
}