package shop.awesomejump.controller;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.sf.json.JSONArray;
import shop.awesomejump.domain.CategoryDTO;
import shop.awesomejump.domain.ProImageDTO;
import shop.awesomejump.domain.ProductDTO;
import shop.awesomejump.domain.ProductOptionDTO;
import shop.awesomejump.mapper.ProductMapper;
import shop.awesomejump.service.ICategoryDAO;
import shop.awesomejump.service.IOptionDAO;
import shop.awesomejump.service.IProductDAO;

@RequestMapping("/admin")
@Controller
public class ProductController {

	@Inject
	private ICategoryDAO cateDao;

	@Autowired
	private IProductDAO pDao;

	@Autowired
	private IOptionDAO oDao;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	// ?????? ?????? GET
	@RequestMapping(value = "/insertProduct", method = RequestMethod.GET)
	public String insertProduct(Model model) throws Exception {
		System.out.println("insertProduct GET ??????");

		List<CategoryDTO> category = cateDao.category();

		System.out.println("insertProduct CategoryDTO ==> " + category);

		model.addAttribute("category", JSONArray.fromObject(category));

		System.out.println("insertProduct JSONArray ==> " + JSONArray.fromObject(category));

		return "/admin/insertProduct";
	}

	// ?????? ?????? POST
	@Transactional
	@RequestMapping(value = "/insertProduct", method = RequestMethod.POST)
	public void insertProduct(ProductDTO pDto, @RequestParam("category_main") String category_main,
			@RequestParam("category_mid") String category_mid, @RequestParam("category_sub") String category_sub,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes rttr) throws Exception {

		logger.info("insertProduct ProductImage ============> " + pDto.getImageList());

		pDto.setCategory_main(category_main);
		pDto.setCategory_mid(category_mid);
		pDto.setCategory_sub(category_sub);

		System.out.println("insertProduct category_main ==> " + category_main);
		System.out.println("insertProduct category_mid ==> " + category_mid);
		System.out.println("insertProduct category_sub ==> " + category_sub);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		pDao.insertProduct(pDto);

		logger.info("insertProduct POST ProductDTO ==> " + pDto);

		String[] product_option_color = request.getParameterValues("product_option_color");
		String[] product_option_size = request.getParameterValues("product_option_size");

		logger.info("insertProduct product_option_color -> " + product_option_color);
		logger.info("insertProduct product_option_size -> " + product_option_size);

		int insertOption = 0;

		// ??????, ?????? ??? ?????? ??? ??????
		for (int i = 0; i < product_option_size.length; i++) {
			for (int j = 0; j < product_option_color.length; j++) {
				ProductOptionDTO optionDto = new ProductOptionDTO();
				optionDto.setProduct_no(pDto.getProduct_no());
				optionDto.setProduct_option_size(product_option_size[i]); // 1 2
				if (!product_option_color[j].isEmpty()) {
					optionDto.setProduct_option_color(product_option_color[j]); // ?????? ??????
					insertOption = oDao.insertOption(optionDto);
				}
			}

		}

		if (insertOption > 0) {
			out.println("<script>alert('??????????????? ?????????????????????.'); location.href='/admin/listProduct';</script>");
		} else {
			out.println("<script>alert('??????????????? ?????????????????????.'); history.go(-1);</script>");
		}

	}

	// ?????? ?????? GET
	@RequestMapping(value = "/listProduct", method = RequestMethod.GET)
	public String listProduct(Model model, @RequestParam(name = "keyword", required = false) String product_name)
			throws Exception {

		/* required = false ??? ?????????????????? ?????? ???????????? ????????? ????????? ???????????? ?????????. */

		logger.info("listProduct GET ??????");

		List<ProductDTO> list = new ArrayList<ProductDTO>();
		if (product_name != null) {
			list = pDao.serchProduct(product_name);
		} else {
			list = pDao.listProduct();
		}

		model.addAttribute("list", list);

		return "/admin/listProduct";
	}

	// ?????? ?????? ?????? GET
	@RequestMapping(value = "/detailProduct", method = RequestMethod.GET)
	public void detailProduct(Model model, @RequestParam("product_no") int product_no) throws Exception {
		logger.info("detailProduct GET ??????");

		ProductDTO pDto = pDao.detailProduct(product_no);

		List<ProductOptionDTO> option = oDao.listOption(product_no);

		logger.info("option List ==> " + option.toString());

		// option ???????????? ?????? ?????? ??? ???????????? ??? ?????????
		List<ProductOptionDTO> resultList = option.stream().distinct().collect(Collectors.toList());

		logger.info("detailProduct pDto ==> " + pDto);
		logger.info("detailProduct option ==> " + option);

		model.addAttribute("detail", pDto);
		model.addAttribute("option", resultList);

	}

	// ?????? ?????? GET
	@RequestMapping(value = "/modifyProduct", method = RequestMethod.GET)
	public String modifyProduct(Model model, @RequestParam("product_no") int product_no) throws Exception {
		logger.info("modifyProduct GET ??????");
		System.out.println("modifyProduct product_no ==> " + product_no);

		ProductDTO pDto = pDao.detailProduct(product_no);
		List<ProductOptionDTO> option = oDao.listOption(product_no);

		model.addAttribute("modify", pDto);
		model.addAttribute("modifyOption", option);
		model.addAttribute("optionColor", JSONArray.fromObject(option));

		return "/admin/modifyProduct";
	}

	// ?????? ?????? POST
	@Transactional
	@RequestMapping(value = "/modifyProduct", method = RequestMethod.POST)
	public void modifyProduct(HttpServletResponse response, HttpServletRequest request, ProductDTO pDto)
			throws Exception {
		logger.info("modifyProduct POST ??????");
		System.out.println("modifyProduct ProductDTO ==> " + pDto);

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int modifyProduct = 0;

		modifyProduct = pDao.modifyProduct(pDto);

		if (modifyProduct == 1 && pDto.getImageList() != null && pDto.getImageList().size() > 0) {
			pDao.deleteImageAll(pDto.getProduct_no());

			pDto.getImageList().forEach(attach -> {

				attach.setProduct_no(pDto.getProduct_no());
				try {
					pDao.insertProductImage(attach);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		String[] product_option_color = request.getParameterValues("product_option_color");
		String[] product_option_size = request.getParameterValues("product_option_size");

		logger.info("insertProduct product_option_color -> " + product_option_color);
		logger.info("insertProduct product_option_size -> " + product_option_size);

		int insertOption = 0;

		// ?????? ????????? ????????? ???????????? ??? ??????
		List<ProductOptionDTO> selectOption = oDao.listOption(pDto.getProduct_no());
		System.out.println("insertProduct selectOption ====> " + selectOption.size());

		// ????????? ????????? ??????????????? ?????? ?????? ??????
		if (selectOption.size() != 0) {
			oDao.deleteOption(pDto.getProduct_no());
		}
		// ??????, ?????? ??? ?????? ??? ??????
		for (int i = 0; i < product_option_size.length; i++) {
			for (int j = 0; j < product_option_color.length; j++) {
				ProductOptionDTO optionDto = new ProductOptionDTO();
				optionDto.setProduct_no(pDto.getProduct_no());
				optionDto.setProduct_option_size(product_option_size[i]); // 1 2
				if (!product_option_color[j].isEmpty()) {
					optionDto.setProduct_option_color(product_option_color[j]); // ?????? ??????
					insertOption = oDao.insertOption(optionDto);
				}
			}
		}

		if (modifyProduct > 0 && insertOption > 0) {
			out.println("<script>alert('??????????????? ?????????????????????.'); location.href='/admin/listProduct';</script>");
		} else {
			out.println("<script>alert('??????????????? ?????????????????????.'); history.go(-1);</script>");
		}

	}

	File imgUploadPath = null;

	// ?????? ????????? ??????
	@PostMapping(value = "/uploadProductImg", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ProImageDTO>> uploadProductImg(MultipartFile[] uploadFile) throws Exception {
		logger.info("uploadProductImg POST ??????");

		for (MultipartFile multipartFile : uploadFile) {

			File checkfile = new File(multipartFile.getOriginalFilename());
			logger.info("multipartFile.getOriginalFilename -----> " + multipartFile.getOriginalFilename());
			String type = null;

			type = Files.probeContentType(checkfile.toPath());
			logger.info("MIME TYPE : " + type);

			if (!type.startsWith("image")) {

				List<ProImageDTO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
			}
		} // for end

		// ?????? ?????? ??????
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		String datePath = str.replace("-", File.separator);

		logger.info("datePath ==> " + datePath);

		imgUploadPath = new File(uploadPath + File.separator + "product" + File.separator + datePath);

		/////////////////////////////////////////////////
		File imageUploadPath = new File("/product/" + datePath);
		/////////////////////////////////////////////////

		logger.info("imgUploadPath ==> " + imgUploadPath);

		if (imgUploadPath.exists() == false) {
			imgUploadPath.mkdirs();
		}

		// ????????? ?????? ?????? ??????
		List<ProImageDTO> list = new ArrayList<>();

		for (MultipartFile multipartFile : uploadFile) {

			// ????????? ?????? ??????
			ProImageDTO piDto = new ProImageDTO();

			logger.info("?????? ?????? : " + multipartFile.getOriginalFilename());
			logger.info("?????? ?????? : " + multipartFile.getContentType());
			logger.info("?????? ?????? : " + multipartFile.getSize());

			// ?????? ??????
			String uploadFileName = multipartFile.getOriginalFilename();
			// ?????? ??????
			String uploadFileType = multipartFile.getContentType();

			piDto.setPro_image_name(uploadFileName);
			/////////////////////////////////////////////////
			piDto.setPro_image_path(imageUploadPath.toString());
			/////////////////////////////////////////////////
			piDto.setPro_image_type(uploadFileType);

			// uuid ?????? ?????? ??????
			String uuid = UUID.randomUUID().toString();

			piDto.setPro_image_uuid(uuid);

			System.out.println("piDto =========================> " + piDto);

			uploadFileName = uuid + "_" + uploadFileName;

			// ?????? ??????, ?????? ????????? ?????? File ??????
			File saveFile = new File(imgUploadPath, uploadFileName);

			System.out.println("saveFile ============> " + saveFile);

			// ????????? ?????? ??????
			multipartFile.transferTo(saveFile);

			list.add(piDto);
			System.out.println("uploadImage List ==========> " + list);
		} // for end
		ResponseEntity<List<ProImageDTO>> result = new ResponseEntity<List<ProImageDTO>>(list, HttpStatus.OK);
		System.out.println("result ===============> " + result);
		return result;
	}

	// ?????? ????????? ??????
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		logger.info("deleteFile POST ??????");
		logger.info("deleteFile POST fileName ==> " + fileName);

		File file = null;

		try {

			logger.info("deleteFile imgUploadPath ==> " + imgUploadPath);
			// ????????? ????????? ??????
			/* file = new File(imgUploadPath + URLDecoder.decode(fileName, "UTF-8")); */
			file = new File(URLDecoder.decode(uploadPath + fileName, "UTF-8"));
			logger.info("deleteFile file ==> " + file);
			file.delete();

			// ?????? ????????? ??????
//			String originFileName = file.getAbsolutePath().replace("s_", "");
//
//			logger.info("originFileName ==> " + originFileName);
//
//			file = new File(originFileName);
//
//			file.delete();
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
		}

		return new ResponseEntity<String>("success", HttpStatus.OK);

	}

	// ?????? ????????? ?????? ??????
	@GetMapping(value = "/getImageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<ProImageDTO>> getImageList(int product_no, Model model) throws Exception {

		logger.info("getImageList ?????? => " + product_no);

		return new ResponseEntity<List<ProImageDTO>>(productMapper.getImageList(product_no), HttpStatus.OK);
	}

}
