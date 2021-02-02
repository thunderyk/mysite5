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
		return dao.getBoard(no);
	}

	public void insertBoard(RBoardVo rBoardVo) {
		if(rBoardVo.getGroup_no() != 0) {
			//order 순서 변경
			dao.updateOrder(rBoardVo);
		}
		dao.insertBoard(rBoardVo);
	}
	public void modifyBoard(RBoardVo rBoardVo) {
		dao.modifyBoard(rBoardVo);
	}
	//부모 댓글이 지워지면 자식 댓글도 다 지워짐
	public void deleteBoard(int no) {
		//나에 대한 group_id와 depth를 가져옴.
		//list로 같은 group_id 중 depth가 높거나 같은거 가져옴 정렬해서
		//for문으로 내 위치로 이동 나 밑에서부터 depth가 낮을 때까지 삭제
		//그리고 for문 나와서 나 자신 삭제
		//그러면 나부터 자식까지의 댓글들만 삭제됨
		
		RBoardVo rboardVo = dao.getBoardForDelete(no);
		
		List<RBoardVo> rboardVoList = dao.getBoardList(rboardVo);
		
		for(int i=0;i<rboardVoList.size();i++) {
			if(rboardVo.getNo() == rboardVoList.get(i).getNo()) {
				for(int j=i+1;j<rboardVoList.size();j++) {
					if(rboardVo.getDepth() < rboardVoList.get(j).getDepth()) {
						dao.deleteBoard(rboardVoList.get(j).getNo());
					}else {
						break;
					}
					
				}
				break;
			}
		}
		dao.deleteBoard(no);
		//dao.deleteBoard(rboardVo);
	}
}
