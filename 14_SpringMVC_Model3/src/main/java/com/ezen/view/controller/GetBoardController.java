package com.ezen.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

public class GetBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�Խñ� �� ��ȸ ó��");
		// 1. �˻��� �Խñ� ��ȣ ����
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		// 2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setSeq(seq);
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		
		// 3. �˻� ����� ���ǿ� �����ϰ� �� ȭ���� ����
		HttpSession session = request.getSession();
		session.setAttribute("board", board);
		
		return "getBoard";
	}

}
