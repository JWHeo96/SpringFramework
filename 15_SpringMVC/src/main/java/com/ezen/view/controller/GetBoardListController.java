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
		// 1. 사용자 입력 정보 추출
		// 2. DB 연동 처리
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		ModelAndView modelView = new ModelAndView();
		// DB에서 조회한 내용을 화면에 전달하기 위해 내장객체에 저장
		modelView.addObject("boardList",boardList);
		// 응답 화면 저장
		modelView.setViewName("getBoardList");
		
		return modelView;
	}
	
}
