package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("/guest")
public class GuestBookController {

	@Autowired
	private GuestBookService guestService;
	
	@RequestMapping("/addList")
	public String addList(Model model) {
		
		List<GuestBookVo> guestBookList = guestService.getAllList();
		
		model.addAttribute("guestBookList", guestBookList);
		
		return"/guestbook/addList";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		
		guestService.addGuestBook(guestBookVo);
		
		return"redirect:./addList";
	}
	
	@RequestMapping("/deleteForm")
	public String deleteForm() {
		
		return"/guestbook/deleteForm";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestBookVo guestBookVo) {
		
		guestService.deleteGuestBook(guestBookVo);
		
		return"redirect:./addList";
	}
	
	@RequestMapping("/ajaxList")
	public String ajaxList() {
		return "/guestbook/ajaxList";
	}
	
	
	
}
