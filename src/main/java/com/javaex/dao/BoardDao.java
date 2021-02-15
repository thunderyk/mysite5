package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getBoardList(int begin, int end,String searchWay, String searchData){
	
		Map<String,Object> boardListMap = new HashMap<String,Object>();
		
		boardListMap.put("begin", begin);
		boardListMap.put("end", end);
		
		if(searchData == null) {	
			return sqlSession.selectList("board.getList",boardListMap);
		}else {
			searchData = "%"+searchData+"%";
			boardListMap.put("searchData", searchData);
			boardListMap.put("searchWay", searchWay);
			
			return sqlSession.selectList("board.getList",boardListMap);
		}
			
	}
	
	public List<BoardVo> getBoardList2(String keyword){
		
		return sqlSession.selectList("board.getList2",keyword);	
	}
	
	public List<BoardVo> getBoardList3(String keyword, int begin, int end){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("begin", begin);
		map.put("end", end);
		
		return sqlSession.selectList("board.getList3",map);	
	}
	
	public double getTotalCount(String searchWay, String searchData) {
		
		if(searchData == null) {
			return sqlSession.selectOne("board.getCountData");
		}
		else {
			Map<String,Object> searchMap = new HashMap<String,Object>();
			
			searchData = "%"+searchData+"%";
			
			searchMap.put("searchData", searchData);
			searchMap.put("searchWay", searchWay);
			
			return sqlSession.selectOne("board.getCountData", searchMap);
		}
	}
	
	public int selectTotalCnt(String keyword) {
	
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
	}
	
	public void insertBoard(BoardVo boardVo) {
		sqlSession.insert("board.boardInsert",boardVo);
	}
	
	public BoardVo readBoard(int readNum) {
		return sqlSession.selectOne("board.readBoard",readNum);
	}
	public void updateHit(int no) {
		sqlSession.update("board.updateHit",no);
	}
	
	public void delete(int no) {
		sqlSession.delete("board.deleteBoard",no);
	}
	public void modifyBoard(BoardVo boardVo) {
		sqlSession.update("board.updateBoard",boardVo);
	}
}
