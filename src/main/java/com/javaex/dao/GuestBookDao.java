package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestBookVo> getAllList(){
		
		return sqlSession.selectList("guest.getAllList");
	}
	
	public void addGuestBook(GuestBookVo guestBookVo) {
		
		sqlSession.insert("guest.addGuestBook", guestBookVo);
	}
	public void deleteGuestBook(GuestBookVo guestBookVo) {
		
		sqlSession.delete("guest.deleteGuestBook", guestBookVo);
	}
	public void inserSelectKey(GuestBookVo guestBookVo) {
		sqlSession.insert("guest.ajaxWrite", guestBookVo);
		
	}
	public GuestBookVo selectOne(int no) {
		return sqlSession.selectOne("guest.getOne",no);
	}
}
