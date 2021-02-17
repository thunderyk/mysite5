package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value= "crtPage",required= false, defaultValue= "1") int crtPage,
					   @RequestParam(value= "keyword",required= false, defaultValue= "") String keyword,
					   Model model) {
	
		Map<String,Object> pMap = galleryService.getList(keyword,crtPage);
		model.addAttribute("pMap",pMap);

		return "/gallery/list";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(@RequestParam("no") int no) {
	
		galleryService.delete(no);
		
		return no;
	}
}
