package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	GalleryDao galleryDao;
	
	public List<GalleryVo> getList(){
		
		return galleryDao.getList();
	}

	public void delete(int no) {
		galleryDao.delete(no);
		
	}
}
