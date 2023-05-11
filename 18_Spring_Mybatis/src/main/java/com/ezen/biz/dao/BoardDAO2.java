package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.BoardVO;

@Repository("boardDAO2")
public class BoardDAO2 {

	@Autowired
	private SqlSessionTemplate mybatis;
	

	public void insertBoard(BoardVO vo) {

		// 부모 클래스의 mybatis을 호출하여 세션을 얻어온다.
		mybatis.insert("BoardMapper.insertBoard", vo);
		
	}

	public void updateBoard(BoardVO vo) {

		mybatis.update("BoardMapper.updateBoard", vo);
	
	}

	public void deleteBoard(BoardVO vo) {

		mybatis.delete("BoardMapper.deleteBoard", vo);
	
	}

	public BoardVO getBoard(BoardVO vo) {

		return (BoardVO) mybatis.selectOne("BoardMapper.getBoard", vo);

	}

	public List<BoardVO> getBoardList(BoardVO vo) {

		return mybatis.selectList("BoardMapper.getBoardList", vo);

	}

}
