package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<RBoardVo> getList(){
		return 	sqlSession.selectList("rboard.getList");
	}
	
	public RBoardVo getBoard(int no) {
		return sqlSession.selectOne("rboard.getBoard",no);
	}
	public RBoardVo getBoardForDelete(int no) {
		return sqlSession.selectOne("rboard.getBoardForDelete",no);
	}
	public void updateHit(int no) {
		sqlSession.update("rboard.updateHit",no);
	}
	public void updateOrder(RBoardVo rBoardVo) {
		sqlSession.update("rboard.updateOrder", rBoardVo);
	}
	public void updateParentOrder(RBoardVo rBoardVo) {
		sqlSession.update("rboard.updateParentOrder", rBoardVo);
	}
	public void insertBoard(RBoardVo rBoardVo) {
		sqlSession.insert("rboard.write",rBoardVo);
	}
	public void modifyBoard(RBoardVo rBoardVo) {
		sqlSession.update("rboard.updateBoard", rBoardVo);
	}
	public void deleteBoard(int no) {
		sqlSession.delete("rboard.deleteBoard", no);
	}
	public List<RBoardVo> getBoardList(RBoardVo rboardVo){
		return 	sqlSession.selectList("rboard.getListGroup", rboardVo);
	}
}
