package com.ezen.biz.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.BoardVO;

//@Repository("boardDAO")
public class BoardDAO extends SqlSessionDaoSupport {

	
	// 부모클래스 세션 생성 메소드를 호출하여 데이터베이스에 연결한 세션 생성
	//@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		
		super.setSqlSessionFactory(sqlSessionFactory);
		
	}
	
	

	public void insertBoard(BoardVO vo) {

		// 부모 클래스의 getSqlSession()을 호출하여 세션을 얻어온다.
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
