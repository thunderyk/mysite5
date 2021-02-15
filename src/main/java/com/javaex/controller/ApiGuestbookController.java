package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestBookService guestService;
	
	//글작성(ajax)
	@RequestMapping("/write")
	@ResponseBody
	public GuestBookVo ajaxList(@ModelAttribute GuestBookVo guestBookVo) {
		return guestService.writeResultVo(guestBookVo);	
	}
	
	@RequestMapping("/write2")
	@ResponseBody
	public GuestBookVo ajaxList2(@RequestBody GuestBookVo guestBookVo) {
		return guestService.writeResultVo(guestBookVo);	
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public List<GuestBookVo> list() {
		
		
		return guestService.getAllList();
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(@ModelAttribute GuestBookVo guestBookVo) {
		return guestService.deleteGuestBook(guestBookVo);
	}
}
