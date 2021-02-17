package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUpService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("fileUpload")
public class FileUploadController {

	@Autowired
	private FileUpService fileUploadService;
	
	@RequestMapping("/form")
	public String form() {
		System.out.println("FileUpController.form()");
		return "/fileUpload/form";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,
						 Model model) {
		System.out.println("FileUpController.upload()");
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		String saveName = fileUploadService.restore(file);
		
		System.out.println(saveName);
		
		model.addAttribute("saveName",saveName);
		
		return "/fileUpload/result";
	}
	//위에는 수업
	
	//
	@RequestMapping("/upload2")
	public String upload2(@ModelAttribute GalleryVo galleryVo,
						 @RequestParam("file") MultipartFile file,
						 Model model) {
		
		fileUploadService.restore2(file, galleryVo);
		
		return "redirect:../gallery/list";
	}
	
	
	
	
}
