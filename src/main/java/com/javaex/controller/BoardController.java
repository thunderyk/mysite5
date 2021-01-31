package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(name="searchWay",required=false) String searchWay,
					   @RequestParam(name="searchData",required=false) String searchData,
					   @RequestParam(name="currentPage",required=false, defaultValue="1") int currentPage,
					   Model model) {
		//required=false를 쓰면 searchWay나 searchData라는 파라미터가 없어도 사용할 수 있고,
		//대신에 searchWay나 searchData에 null이 들어감
		//int로 하면 int형에는 null값이 들어갈 수 없다는 에러가 생김 그러므로 defaultValue를 지정해 주면 됨.
		//페이징을 위해서 현재 페이지를 1로 지정(currentPage에 값이 없을 경우 즉, 맨처음 board/list에 접속했을 경우
		
		int dataPerPage = 10; //한 페이지에 보여줄 데이터(게시글의 수)
		double totalData = 0; //총 대이터
		
		if(searchWay==null) {
			totalData = boardService.getTotalCount(searchWay,searchData);
			//검색 방법이 없는 경우 null일 경우 즉, 검색이 없으면 전체 데이터의 갯수를 불러옴
		}else {
			model.addAttribute("searchData",searchData); //list.jsp에서 검색 칸에 다시 보여주고 페이징에 필요해서 넘김
			model.addAttribute("searchWay",searchWay); //list.jsp에서 select되는 것을 정해야 하고 페이징에 필요해서 넘김
			totalData = boardService.getTotalCount(searchWay,searchData);
			//검색 방법과 내용이 있으로 그에 해당하는 데이터의 갯수를 가져옴
		}
		
		
		double pageCount = Math.ceil(totalData/dataPerPage); //페이지의 수 개산
		
		int begin= dataPerPage*(currentPage-1)+1; //해당 페이지의 첫번 째 데이터를 가져오기 위해서 2페이지의 경우 11번부터
		int end=  (dataPerPage*currentPage); //해당 페이지의 마자막(10번째) 데이터를 가져오기 위해서 2페이지의 경우 20번
		
		//그래서 리스트를 가져올 때 페이지의 시작 번호, 페이지의 끝 번호, 검색 방법, 검색어를 넘김
		//그리고 10개를 가져옴
		List<BoardVo> pageBoardList = boardService.getBoardList(begin, end, searchWay, searchData);
		
		model.addAttribute("pageBoardList",pageBoardList); //10개를 List에 넣어서 넘김
		model.addAttribute("pageCount",pageCount); //총 페이지의 수를 넣어서 넘김
		model.addAttribute("currentPage",currentPage); //현재 페이지를 넣어서 넘김
		
		return "board/list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "board/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(@RequestParam("title") String title,
						@RequestParam("content") String content,
						HttpSession session) {
		
		
		UserVo userVo = (UserVo)session.getAttribute("authorMember"); //세션에서 userVo의 no가 필요 해서 가져옴
		BoardVo boardVo = new BoardVo(title,content,userVo.getNo()); //파라미터로 받은 title, content와 세션의 userVo의 no로 boardVo 생성
		boardService.insertBoard(boardVo); //boardService로 boardVo를 넘김
		
		return "redirect:./list";
	}
	
	@RequestMapping("/deleteBoard")
	public String deleteBoard(@RequestParam("deleteNum") int no) {
		boardService.delete(no);
		return "redirect:./list";
	}
	
	@RequestMapping("/read")
	public String read(@RequestParam("readNum") int readNum,
					   Model model) {
		
		boardService.updateHit(readNum);
		BoardVo boardVo = boardService.readBoard(readNum);
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	@RequestMapping("/mForm")
	public String modifyForm(@RequestParam("modifyNum") int modifyNum,
							 Model model) {
		
		BoardVo boardVo = boardService.readBoard(modifyNum);
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}
	@RequestMapping("/modifyBoard")
	public String modifyBoard(@ModelAttribute BoardVo boardVo) {
		
		boardService.modifyBoard(boardVo);
		
		return "redirect:./read?readNum="+boardVo.getNo();
	}
}
