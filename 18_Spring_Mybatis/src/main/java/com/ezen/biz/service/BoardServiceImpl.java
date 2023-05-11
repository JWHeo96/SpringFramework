package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dao.BoardDAO2;
import com.ezen.biz.dto.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	//private BoardDAO boardDAO; // Mybatis ���� ù��° ���
	private BoardDAO2 boardDAO; // Mybatis ���� �ι�° ���
	
	
	//private LogAdvice log;
	//private Log4jAdvice log; // �α� Ŭ������ ���濡 ���� ����
	

	@Override
	public void insertBoard(BoardVO vo) {
		//log.printLog();
		//log.printLogging();
		
		/*
		if (vo.getSeq() == 0) {
			throw new IllegalArgumentException("0�� ���� ����� �� �����ϴ�.");
		}
		*/
		
		// Ʈ����� ���� �׽�Ʈ: ������ seq��ȣ�� ���
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		//log.printLog();
		//log.printLogging();
		
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		//log.printLog();
		//log.printLogging();
		
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		//log.printLog();
		//log.printLogging();
		
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		//log.printLog();
		//log.printLogging();
		
		return boardDAO.getBoardList(vo);
	}

}
