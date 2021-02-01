package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {

	@Autowired
	private RBoardDao dao;
	
	public List<RBoardVo> getList(){
		
		return dao.getList();
	}
	
	public RBoardVo getBoard(int no,boolean isModify) {
		if(!isModify) {
			dao.updateHit(no);
		}
		return dao.getBoard(no,false);
	}

	public void insertBoard(RBoardVo rBoardVo) {
		if(rBoardVo.getGroup_no() != 0) {
			//order 순서 변경
			dao.updateOrder(rBoardVo);
			//order 순서 변경에 따른 부모 번호 변경
			dao.updateParentOrder(rBoardVo);
		}
		dao.insertBoard(rBoardVo);
	}
	public void modifyBoard(RBoardVo rBoardVo) {
		dao.modifyBoard(rBoardVo);
	}
	//부모 댓글이 지워지면 자식 댓글도 다 지워짐
	public void deleteBoard(int no) {
		//나에 대한 group_id와 depth를 가져옴.
		//나도 삭제 그 밑에도 삭제(group_id가 같은 것과 depth가 낮은것 삭제
		RBoardVo rboardVo = dao.getBoard(no,true);
		dao.deleteBoard(rboardVo);
	}
}
