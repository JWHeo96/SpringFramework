package com.ezen.biz.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ezen.biz.dto.BoardVO;
import com.ezen.biz.util.SqlSessionFactoryBean;

public class BoardDAO {
	// �����ͺ��̽� ���� ��ü
	private SqlSession mybatis;
	
	public BoardDAO() {
		// FactoryBean�� �̿��Ͽ� SqlSeession ��ü�� ���´�.
		mybatis = SqlSessionFactoryBean.getSessionInstance();
	}
	
	public void insertBoard(BoardVO vo) {
		// ù��° ���� : ����� SQL�� id
		// �ι�° ����; : parameterType �Ӽ����� ������ �Ķ���� ��ü
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