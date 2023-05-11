package com.ezen.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.biz.dto.BoardVO;
import com.ezen.biz.service.BoardService;

public class BoardServiceClient {

	private static BoardService boardService;
	
	
	public static void main(String[] args) {


		// 컨테이너를 생성
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		
		
		// 컨테이너에서 boardService 객체를 제공받음.
		boardService = (BoardService) container.getBean("boardService");
		
		
		// 게시글 생성 및 저장
		BoardVO vo = new BoardVO();
		vo.setTitle("mybatis 예제....");
		vo.setWriter("유관순");
		vo.setContent("Mybatis 게시글 생성 예제....");
		boardService.insertBoard(vo);
		
		
		// 게시글 목록 조회
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		
		System.out.println(">>>> 게시글 목록");
		for(BoardVO board : boardList) {
			System.out.println("--- " + board);
		}
		
		container.close();
	}

}
