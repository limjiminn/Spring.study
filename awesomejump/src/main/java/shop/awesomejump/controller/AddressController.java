package shop.awesomejump.controller;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import shop.awesomejump.domain.AddressDTO;
import shop.awesomejump.domain.AddressReceiveVO;
import shop.awesomejump.domain.MemberDTO;
import shop.awesomejump.service.IAddressDAO;

// TODO: 필요에 따라 추가 유효성 검사 기능 추가하기
// TODO: address.jsp 모바일 화면에서 배송지 수정, 추가 모달 css 수정

@Slf4j
@Controller
@Secured("ROLE_USER")
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
	
	private final IAddressDAO addressService;
	
	@GetMapping
	public String address(@AuthenticationPrincipal MemberDTO member, Model model) {
		
		List<AddressDTO> addressList = addressService.selectAddressList(member.getMem_no());

		model.addAttribute("addressList", addressList);
		return "awesomejump/address";
	}
	
	@PostMapping("/newAddress")
	public String newAddress(@ModelAttribute AddressReceiveVO addressRequest,
							 @AuthenticationPrincipal MemberDTO member,
							 RedirectAttributes redirectAttr) {
		
		AddressDTO address = buildAddressDTO(addressRequest, member);
		boolean result = addressService.insertAddress(address);
		log.debug("insertAddress result = {}", result);
		
		redirectAttr.addFlashAttribute("address_no", address.getAddress_no());
		return "redirect:/address";
	}
	
	
	@PostMapping("/modifyAddress")
	public String modifyAddress(@ModelAttribute AddressReceiveVO addressRequest,
							 	@AuthenticationPrincipal MemberDTO member) {
		
		AddressDTO address = buildAddressDTO(addressRequest, member);
		boolean result = addressService.updateAddress(address);
		
		log.debug("updateAddress result = {}", result);
		return "redirect:/address"; 
	}
	
	@PostMapping("/deleteAddress")
	public String deleteAddress(@RequestParam int address_no) {
		
		boolean result = addressService.deleteAddress(address_no);
		
		log.debug("deleteAddress result = {}", result);
		return "redirect:/address";
	}
	
	private AddressDTO buildAddressDTO(AddressReceiveVO addressRequest, MemberDTO member) {
		
		AddressDTO address = AddressDTO.builder()
				.mem_no(member.getMem_no())
				.address_no			   (addressRequest.getAddress_no())
				.address_postcode	   (addressRequest.getAddress_postcode())
				.address_nickname	   (addressRequest.getAddress_nickname().trim())
				.address_receiver_name (addressRequest.getAddress_receiver_name().trim())
				.address_receiver_phone(addressRequest.getAddress_receiver_phone())
				.build();
		
		List<String> addressList = addressRequest.getAddressList();
		String addressDetail = addressList.remove(0);
		
		for (String addr : addressList) {
			if (!addr.trim().equals("")) {
				addressDetail = addressDetail.concat("，").concat(addr.trim());
			}
		}
		address.setAddress_detail(addressDetail);
		return address;
	}
	
}