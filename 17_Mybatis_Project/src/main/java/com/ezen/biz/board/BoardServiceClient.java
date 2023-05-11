package com.ezen.biz.board;

import java.util.List;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

public class BoardServiceClient {

	public static void main(String[] args) {
		// BoardDAO 객체 생성
		BoardDAO boardDAO = new BoardDAO();
		
		// 게시글 생성 및 저장
		BoardVO vo = new BoardVO();
		vo.setTitle("Mybatis 예제...");
		vo.setWriter("허장욱");
		vo.setContent("Mybatis 게시글 생성 예제...");
		
		boardDAO.insertBoard(vo);
		
		// 게시글 목록 조회
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		for(BoardVO board : boardList) {
			System.out.println(board);
		}
	}
}
