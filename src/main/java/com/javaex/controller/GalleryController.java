package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list(Model model) {
	
		List<GalleryVo> galleryList = galleryService.getList();
		model.addAttribute("galleryList",galleryList);
		
		return "/gallery/list";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(@RequestParam("no") int no) {
	
		galleryService.delete(no);
		
		return no;
	}
}
