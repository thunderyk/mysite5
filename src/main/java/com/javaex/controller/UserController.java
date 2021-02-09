package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/loginForm",method= {RequestMethod.POST,RequestMethod.GET})
	public String loginForm() {
		
		return "user/loginForm";
		
	}
	
	@RequestMapping(value="/login",method= {RequestMethod.POST,RequestMethod.GET})
	public String login(@ModelAttribute UserVo userVo, HttpSession session, Model model) {
		
		UserVo authorUser = userService.login(userVo);
		
		if(authorUser != null) {
			//로그인 성공
			session.setAttribute("authorMember", authorUser);
			
			return "redirect:../";
			
		}else {
			//로그인 실패
			return "redirect:loginForm?result=fail";
		}
	}
	@RequestMapping(value="/logout",method= {RequestMethod.POST,RequestMethod.GET})
	public String logout(HttpSession session) {
		session.removeAttribute("authorMember");
		return "redirect:../";
	}
	
	
	@RequestMapping(value="/joinForm",method= {RequestMethod.POST,RequestMethod.GET})
	public String joinForm() {
		
		return "user/joinForm";
		
	}
	
	@RequestMapping(value="/join",method= {RequestMethod.POST,RequestMethod.GET})
	public String join(@ModelAttribute UserVo userVo) {
		
		userService.join(userVo);
		
		return "user/joinOk";
		
	}
	
	@RequestMapping(value="/modifyForm",method= {RequestMethod.POST,RequestMethod.GET})
	public String modifyForm(HttpSession session, Model model) {
		
		UserVo userVo = (UserVo)session.getAttribute("authorMember");
		userVo = userService.getMember(userVo.getNo());
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
		
	}
	
	@RequestMapping(value="/modify",method= {RequestMethod.POST,RequestMethod.GET})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		
		userService.update(userVo);
		
		UserVo authorMember = userService.getSession(userVo.getNo());
		
		session.setAttribute("authorMember", authorMember);
		
		return "user/modifyForm";
		
	}
	
	@RequestMapping(value="/idcheck",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String idCheck(@RequestParam("id") String id) {
		
		String result = userService.idCheck(id);
		
		return result;//"redirect:/user/joinForm";
	}
}
