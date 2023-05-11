package com.ezen.biz.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.BoardVO;

//@Repository("boardDAO")
public class BoardDAO extends SqlSessionDaoSupport {

	
	// �θ�Ŭ���� ���� ���� �޼ҵ带 ȣ���Ͽ� �����ͺ��̽��� ������ ���� ����
	//@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		
		super.setSqlSessionFactory(sqlSessionFactory);
		
	}
	
	

	public void insertBoard(BoardVO vo) {

		// �θ� Ŭ������ getSqlSession()�� ȣ���Ͽ� ������ ���´�.
		getSqlSession().insert("BoardMapper.insertBoard", vo);
		
	}

	public void updateBoard(BoardVO vo) {

		getSqlSession().update("BoardMapper.updateBoard", vo);
	
	}

	public void deleteBoard(BoardVO vo) {

		getSqlSession().delete("BoardMapper.deleteBoard", vo);
	
	}

	public BoardVO getBoard(BoardVO vo) {

		return (BoardVO) getSqlSession().selectOne("BoardMapper.getBoard", vo);

	}

	public List<BoardVO> getBoardList(BoardVO vo) {

		return getSqlSession().selectList("BoardMapper.getBoardList", vo);

	}

}
