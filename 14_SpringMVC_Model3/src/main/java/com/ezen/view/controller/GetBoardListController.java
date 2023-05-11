package com.ezen.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

public class GetBoardListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó��
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		// 3. �˻� ����� ���ǿ� �����ϰ� ��� ȭ������ �̵��Ѵ�.
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);
		
		return "getBoardList";
	}
	
}
