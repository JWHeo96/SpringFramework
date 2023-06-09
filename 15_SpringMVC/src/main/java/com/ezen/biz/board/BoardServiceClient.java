package com.ezen.biz.board;

import java.util.*;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.biz.dto.BoardVO;
import com.ezen.biz.service.BoardService;

public class BoardServiceClient {
	
	private static BoardService boardService;
	
	public static void main(String[] args) {
		// 스프링 컨테이너 생성
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");	
		
		// boardService 객체 생성 요청
		// 아래의 내용을 @Autowired로 대신 처리
		boardService = (BoardService)container.getBean("boardService");
		
		// 게시글 등록
		BoardVO board = new BoardVO();
		board.setSeq(9999);	// 트랜잭션 관리 테스트 목적
		board.setTitle("게시글 제목");
		board.setWriter("장보고");
		board.setContent("스프링 예제 게시글입니다.");
		
		// 게시글 입력 테스트
		boardService.insertBoard(board);
		
		// 게시글 목록 조회
		List<BoardVO> boardList = boardService.getBoardList();

		System.out.println("<<< 게시글 목록 >>>");
		for(BoardVO vo : boardList) {
			System.out.println(vo);
		}
		
		// 게시글 상세 조회
		BoardVO vo1 = new BoardVO();
		vo1.setSeq(3);
		System.out.println("<<< 게시글 상세조회 >>>");
		System.out.println(boardService.getBoard(vo1));
		
		// 게시글 수정
		BoardVO vo2 = new BoardVO();
		vo2.setSeq(4);
		vo2.setTitle("");
		vo2.setWriter("");
		vo2.setContent("");
		System.out.println("<<< 게시글 수정 >>>");
		boardService.updateBoard(vo2);
		
		// 게시글 삭제
		BoardVO vo3 = new BoardVO();
		vo3.setSeq(4);
		System.out.println("<<< 게시글 삭제 >>>");
		boardService.deleteBoard(vo3);
		
		container.close();
	}
}
