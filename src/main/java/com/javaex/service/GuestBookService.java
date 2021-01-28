package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao guestDao;
	
	public List<GuestBookVo> getAllList(){
		
		return guestDao.getAllList();
	}
	
	public void addGuestBook(GuestBookVo guestBookVo) {
		guestDao.addGuestBook(guestBookVo);
	}
	
	public void deleteGuestBook(GuestBookVo guestBookVo) {
		guestDao.deleteGuestBook(guestBookVo);
	}
}
