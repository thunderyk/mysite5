package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.FileUploadDao;
import com.javaex.vo.GalleryVo;

@Service
public class FileUpService {

	@Autowired
	FileUploadDao fileUploadDao;
	
	public String restore(MultipartFile file) {
		
		String saveDir = "D:\\FileUp";
		
		//db저장할 정보 수집
		
		String orgFileName = file.getOriginalFilename();
		System.out.println("orgFileName: "+orgFileName);
		
		String exName = orgFileName.substring(orgFileName.lastIndexOf("."));
		System.out.println("exName: "+exName);
		
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println("saveName: "+saveName);
		
		String filePath = saveDir+"\\"+saveName;
		System.out.println("filePath: "+filePath);
		
		long fileSize = file.getSize();
		System.out.println("fileSize: "+fileSize);
		//서버 하드디스크에 저장
		try {
			byte[]fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			bos.write(fileData);
			bos.close();
			
		}catch(Exception e) {
			System.out.println("FileUpService restore Error: "+e);
		}
		
		return saveName;
	}
	
	public String restore2(MultipartFile file, GalleryVo galleryVo) {
		
		String saveDir = "D:\\FileUp";
		
		//db저장할 정보 수집
		
		String orgFileName = file.getOriginalFilename();
		galleryVo.setOrgName(orgFileName);
		
		String exName = orgFileName.substring(orgFileName.lastIndexOf("."));
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		galleryVo.setSaveName(saveName);
		
		String filePath = saveDir+"\\";
		galleryVo.setFilePath(filePath);
		
		long fileSize = file.getSize();
		galleryVo.setFileSize(fileSize);
		
		//서버 하드디스크에 저장
		try {
			byte[]fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath+saveName);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			bos.write(fileData);
			bos.close();
			
		}catch(Exception e) {
			System.out.println("FileUpService restore Error: "+e);
		}
		
		fileUploadDao.insertGallery(galleryVo);
		
		return saveName;
	}
}
