package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class FileUploadDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insertGallery(GalleryVo galleryVo) {
		sqlSession.insert("gallery.insertGallery",galleryVo);
	}
	
}
