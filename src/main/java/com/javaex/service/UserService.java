package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		int count = userDao.insert(userVo);
		return count;
	}
	
	public UserVo login(UserVo userVo) {
		return userDao.loginMember(userVo);
	}
	
	public void update(UserVo userVo) {
		userDao.update(userVo);
	}
	
	public UserVo getSession(int no) {
		return userDao.getSession(no);
	}
	
	public UserVo getMember(int no) {
		return userDao.getMember(no);
	}
}

