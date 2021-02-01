package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/rboard")
public class RBoardController {

	@Autowired
	private RBoardService service;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<RBoardVo> pageBoardList = service.getList();
		
		model.addAttribute("pageBoardList",pageBoardList); //10개를 List에 넣어서 넘김
		
		return "rboard/list";
	}
	
	@RequestMapping("/read")
	public String readBoard(@RequestParam("readNum") int readNum,
					   Model model) {
		
		RBoardVo rBoardVo = service.getBoard(readNum,false);
		model.addAttribute("rBoardVo", rBoardVo);
		
		return "rboard/read";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "rboard/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute RBoardVo rBoardVo,
						HttpSession session) {
		
		UserVo userVo = (UserVo)session.getAttribute("authorMember"); 
		rBoardVo.setUser_no(userVo.getNo());
		
		service.insertBoard(rBoardVo);
		
		return "redirect:./list";
	}
	
	@RequestMapping("/mForm")
	public String modifyForm(@RequestParam("modifyNum") int modifyNum,
							 Model model) {
		
		RBoardVo rBoardVo = service.getBoard(modifyNum,true);
		model.addAttribute("rBoardVo", rBoardVo);
		
		return "rboard/modifyForm";
	}
	
	@RequestMapping("/modifyBoard")
	public String modifyBoard(@ModelAttribute RBoardVo rBoardVo) {
		
		service.modifyBoard(rBoardVo);
		
		return "redirect:./read?readNum="+rBoardVo.getNo();
	}
	
	//자식은 다 지워 져야함
	//부모는 안지워 져야함
	@RequestMapping("/delete")
	public String deleteBoard(@RequestParam("deleteNum") int no) {
		service.deleteBoard(no);
		return "redirect:./list";
	}
	
}
