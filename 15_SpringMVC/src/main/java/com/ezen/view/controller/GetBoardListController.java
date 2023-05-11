package com.ezen.view.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó��
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		ModelAndView modelView = new ModelAndView();
		// DB���� ��ȸ�� ������ ȭ�鿡 �����ϱ� ���� ���尴ü�� ����
		modelView.addObject("boardList",boardList);
		// ���� ȭ�� ����
		modelView.setViewName("getBoardList");
		
		return modelView;
	}
	
}
