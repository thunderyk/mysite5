package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<BoardVo> getBoardList2(String keyword){
		
		return boardDao.getBoardList2(keyword);
	}
	
	public Map<String,Object> getBoardList3(String keyword,int crtPage){
		
		int listCnt = 10;
		
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		int begin= listCnt*(crtPage-1)+1; //해당 페이지의 첫번 째 데이터를 가져오기 위해서 2페이지의 경우 11번부터
		int end=  (listCnt*crtPage); //해당 페이지의 마자막(10번째) 데이터를 가져오기 위해서 2페이지의 경우 20번
		
		List<BoardVo> pageBoardList = boardDao.getBoardList3(keyword,begin,end);
		
		int pageBtnCount = 5;
		
		int totalCount = boardDao.selectTotalCnt(keyword);
		
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		boolean next;
		
		if(endPageBtnNo * listCnt < totalCount) {
			next = true;
		}else {
			next = false;
			endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
		}
		
		boolean prev;
		
		if(startPageBtnNo != 1) {
			prev = true;
		}else {
			prev = false;
		}
		//prev startPageBtnNo, endPageBtnNo,next -> jsp
		
		Map<String,Object> pMap = new HashMap<String,Object>();
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("pageBoardList", pageBoardList);
		
		
		return pMap;
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
