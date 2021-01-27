package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/loginForm",method= {RequestMethod.POST,RequestMethod.GET})
	public String loginForm() {
		
		return "user/loginForm";
		
	}
	
	@RequestMapping(value="/login",method= {RequestMethod.POST,RequestMethod.GET})
	public String login(@ModelAttribute UserVo userVo, HttpSession session, Model model) {
		
		UserVo authorUser = userDao.loginMember(userVo);
		
		if(authorUser != null) {
			//로그인 성공
			session.setAttribute("authorMember", authorUser);
			
			return "redirect:../";
			
		}else {
			//로그인 실패
			model.addAttribute("result","fail");
			return "user/loginForm";
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
		
		int count = userDao.insert(userVo);
		
		System.out.println(count);
		
		return "user/joinOk";
		
	}
	
	@RequestMapping(value="/modifyForm",method= {RequestMethod.POST,RequestMethod.GET})
	public String modifyForm(HttpSession session, Model model) {
		
		UserVo userVo = (UserVo)session.getAttribute("authorMember");
		userVo = userDao.getMember(userVo.getNo());
		model.addAttribute("userVo", userVo);
		
		return "user/modifyForm";
		
	}
	
	@RequestMapping(value="/modify",method= {RequestMethod.POST,RequestMethod.GET})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		
		userDao.update(userVo);
		
		UserVo authorMember = userDao.getSession(userVo.getNo());
		
		session.setAttribute("authorMember", authorMember);
		
		return "user/modifyForm";
		
	}
}
