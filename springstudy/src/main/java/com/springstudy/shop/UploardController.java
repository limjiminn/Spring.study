package com.springstudy.shop;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("fileName : " + fileName);
		
		File file = new File(uploadPath + "\\" + fileName);
		log.info("file : " + file);
		
		ResponseEntity<byte[]> result = null;
		//io 객체
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	//파일 다운로드
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	   public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
	      Resource resource = new FileSystemResource(uploadPath + "\\" + fileName);
	      
	      if(resource.exists() == false) {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	      
	      String resourceName = resource.getFilename();
	      String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);
	      
	      HttpHeaders headers = new HttpHeaders();
	      
	      try {
	         boolean checkIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1); // ex냐 아니냐
	         
	         String downloadName = null;
	         
	         if (checkIE) {
	            downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
	         } else {
	            downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
	         }
	         
	         headers.add("Content-Disposition", "attachment; filename=" + downloadName);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	      
	   }
	
	//폴더 삭제
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile : " + fileName);
		
		 HttpHeaders headers = new HttpHeaders();
		 
		 headers.add("Content-Type", "text/html;charset=UTF-8");
		
		File file;
		
		try {
			file = new File(uploadPath + "\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			if (type.equals("image")) {		//s_를 없앤다 
				String largeFileName = file.getAbsolutePath().replace("\\s_", "\\");
				
				log.info("largeFileName : " + largeFileName);
				
				file = new File(largeFileName);
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
																//상태코드
		return new ResponseEntity<>("정상적으로 처리되었습니다.",headers, HttpStatus.OK);
		
	}
	
	
}















