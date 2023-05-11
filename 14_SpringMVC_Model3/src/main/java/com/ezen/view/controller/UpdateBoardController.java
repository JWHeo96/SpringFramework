package com.ezen.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

public class UpdateBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�Խñ� ���� ó��");
		// 1. ����� �Է� ���� ����
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		// 2. DB ���� ó��
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setSeq(seq);
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.updateBoard(vo);

		// 3. ȭ�� �׺���̼�
		return "getBoardList.do";
	}

}
