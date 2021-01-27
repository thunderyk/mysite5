package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert",userVo);
		return count;
	}
	public void update(UserVo userVo) {
		sqlSession.update("user.update",userVo);
	}
	public UserVo getMember(int no) {
		
		return sqlSession.selectOne("user.getMember",no);
	}
	
	public UserVo loginMember(UserVo userVo) {
		
		return sqlSession.selectOne("user.login",userVo);
	}

	public UserVo getSession(int no) {
		return sqlSession.selectOne("user.getSession",no);
	}
	
}
