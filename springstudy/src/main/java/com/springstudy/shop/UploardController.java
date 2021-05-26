package com.springstudy.shop;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springstudy.shop.board.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j
public class UploardController {
	
	private String uploadPath = "C:\\Users\\jiaez\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\springstudy\\src\\main\\webapp\\resources\\fileupload";
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload Form");
	}
	
	@PostMapping("/uploadFormAction") 		/*uploadFile: uploadFor.jsp의 name */
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		for(MultipartFile multipartFile : uploadFile) {
			log.info("---------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			log.info("Upload File ContentType : " + multipartFile.getContentType());
		}
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	
	private String getFolder() {
		/*날짜 포멧*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		String str = sdf.format(date);
		/*separator : 운영체제의 처리된 경로로 간닷(io객체 검색)*/
		return str.replace("-", File.separator);
	}
	
	private boolean checkImageType(File file) {
		try {  
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		log.info("update ajax post.............");
		List<AttachFileDTO> attachList = new ArrayList<>();
		
		String uploadFolderPath = getFolder();
		
		/*폴더생성*/
		File uploadFolder = new File(uploadPath, getFolder());
		
		log.info("uploadFolder path : " + uploadFolder);
		/*만약 업로드폴더가 존재하지 않는다면*/
		if (uploadFolder.exists() == false) {
			/*생성 폴더가 만들어진다.*/ 
			uploadFolder.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			/*확인*/
			log.info("---------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			log.info("Upload File ContentType : " + multipartFile.getContentType());
			
			AttachFileDTO attachFileDto = new AttachFileDTO();
			/*가공하기*/
			String uploadFileName = multipartFile.getOriginalFilename();
			/*익스에서 \\짤라줌*/
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			/*확인*/
			log.info("only file name : " + uploadFileName);
			
			attachFileDto.setFileName(uploadFileName);
			/* */
			UUID uuid = UUID.randomUUID();
			/*파일네임이 똑같이 들어와도 덮어쓰지않는다*/
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				File saveFile = new File(uploadFolder, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachFileDto.setUuid(uuid.toString());
				attachFileDto.setUploadPath(uploadFolderPath);
				/*이미지인지아닌지 판별*/
				if (checkImageType(saveFile)) {
					attachFileDto.setImage(true);
					
					File thumbnail = new File(uploadFolder, "s_" + uploadFileName);
					
					Thumbnails.of(saveFile).size(100, 100).toFile(thumbnail);
				}
				
				attachList.add(attachFileDto);
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return new ResponseEntity<>(attachList, HttpStatus.OK);
	}
	
}















