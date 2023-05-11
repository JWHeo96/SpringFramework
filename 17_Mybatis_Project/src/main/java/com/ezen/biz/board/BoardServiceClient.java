package com.ezen.biz.board;

import java.util.List;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

public class BoardServiceClient {

	public static void main(String[] args) {
		// BoardDAO ��ü ����
		BoardDAO boardDAO = new BoardDAO();
		
		// �Խñ� ���� �� ����
		BoardVO vo = new BoardVO();
		vo.setTitle("Mybatis ����...");
		vo.setWriter("�����");
		vo.setContent("Mybatis �Խñ� ���� ����...");
		
		boardDAO.insertBoard(vo);
		
		// �Խñ� ��� ��ȸ
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		for(BoardVO board : boardList) {
			System.out.println(board);
		}
	}
}
