package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<GalleryVo> getList(String keyword,int begin, int end){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("begin", begin);
		map.put("end", end);
		
		return sqlSession.selectList("gallery.getList",map);
	}

	public void delete(int no) {
		sqlSession.delete("gallery.delete",no);
	}
	
	public int getTotalCount(String keyword) {
		
		return sqlSession.selectOne("gallery.getTotalCount",keyword);
	}
}
