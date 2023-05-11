package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.common.Log4jAdvice;
import com.ezen.biz.common.LogAdvice;
import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	// private LogAdvice log;
	// private Log4jAdvice log; // �α� Ŭ������ ���濡 ���� ����

	public BoardServiceImpl() {
		// log = new LogAdvice();
		// �α� Ŭ������ ���濡 ���� ����
		// log = new Log4jAdvice();
	}

	@Override
	public void insertBoard(BoardVO vo) {
		// log.printLog(); �α� Ŭ���� ���濡 ���� ����
		// log.printLogging();

		// after-throwing �׽�Ʈ�� ���� ������ ���� �߻�
//		if (vo.getSeq() == 0) {
//			throw new IllegalArgumentException("0�� ���� ����� �� �����ϴ�.");
//		}

		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		// log.printLog(); �α� Ŭ���� ���濡 ���� ����
		// log.printLogging();
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		// log.printLog(); �α� Ŭ���� ���濡 ���� ����
		// log.printLogging();
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		// log.printLog(); �α� Ŭ���� ���濡 ���� ����
		// log.printLogging();
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList() {
		// log.printLog(); �α� Ŭ���� ���濡 ���� ����
		// log.printLogging();
		return boardDAO.getBoardList();
	}

}