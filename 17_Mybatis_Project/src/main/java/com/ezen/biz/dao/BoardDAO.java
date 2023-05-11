package com.ezen.biz.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ezen.biz.dto.BoardVO;
import com.ezen.biz.util.SqlSessionFactoryBean;

public class BoardDAO {
	// 데이터베이스 연결 객체
	private SqlSession mybatis;
	
	public BoardDAO() {
		// FactoryBean을 이용하여 SqlSeession 객체를 얻어온다.
		mybatis = SqlSessionFactoryBean.getSessionInstance();
	}
	
	public void insertBoard(BoardVO vo) {
		// 첫번째 인자 : 실행될 SQL의 id
		// 두번째 인자; : parameterType 속성으로 지정된 파라미터 객체
		mybatis.insert("BoardMapper.insertBoard", vo);
		mybatis.commit();
	}
	public void updateBoard(BoardVO vo) {
		mybatis.update("BoardMapper.updateBoard",vo);
		mybatis.commit();
	}
	public void deleteBoard(BoardVO vo) {
		mybatis.delete("BoardMapper.deleteBoard",vo);
		mybatis.commit();
	}
	public BoardVO getBoard(BoardVO vo) {
		return mybatis.selectOne("BoardMapper.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo){
		return mybatis.selectList("BoardMapper.getBoardList",vo);
	}

}
