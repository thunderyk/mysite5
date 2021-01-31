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
