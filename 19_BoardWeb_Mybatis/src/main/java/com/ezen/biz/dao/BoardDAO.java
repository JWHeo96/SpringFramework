package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.BoardVO;

@Repository("boardDAO")
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// �Խñ� �Է�
	public void insertBoard(BoardVO board) {
		System.out.println("===> JDBC�� insertBoard() ó��");
//		Object[] args = {board.getTitle(), board.getWriter(), board.getContent()};
		// Ʈ����� ó�� �׽�Ʈ��
		
		mybatis.insert("BoardMapper.insertBoard", board);
	}
	
	// �Խñ� ����
	public void updateBoard(BoardVO board) {
		System.out.println("===> JDBC�� updateBoard() ó��");

		mybatis.update("BoardMapper.updateBoard", board);
	}

	// �Խñ� ����
	public void deleteBoard(BoardVO board) {
		System.out.println("===> JDBC�� deleteBoard() ó��");
		
		mybatis.delete("BoardMapper.deleteBoard", board);
	}

	// �Խñ� �� ��ȸ
	public BoardVO getBoard(BoardVO board) {
		System.out.println("===> JDBC�� getBoard() ó��");
		
		return mybatis.selectOne("BoardMapper.getBoard",board);
		
	}
	
	// �Խñ� ��� ��ȸ
	public List<BoardVO> getBoardList(BoardVO board){
		System.out.println("===> JDBC�� getBoardList() ó��");
		
		return mybatis.selectList("BoardMapper.getBoardList_D",board);
	}
}
