package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getBoardList(int begin, int end,String searchWay, String searchData){
		
		return boardDao.getBoardList(begin, end, searchWay, searchData);
	}
	
	public double getTotalCount(String searchWay, String searchData) {
		return boardDao.getTotalCount(searchWay, searchData);
	}
	public void insertBoard(BoardVo boardVo) {
		boardDao.insertBoard(boardVo);
	}
	
	public BoardVo readBoard(int readNum) {
		boardDao.updateHit(readNum);
		return boardDao.readBoard(readNum);
	}
	public void delete(int no) {
		boardDao.delete(no);
	}
	public void modifyBoard(BoardVo boardVo) {
		boardDao.modifyBoard(boardVo);
	}
}
