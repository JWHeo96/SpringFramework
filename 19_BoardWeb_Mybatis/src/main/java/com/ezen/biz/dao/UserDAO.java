package com.ezen.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// ����� ��ȸ
	public UserVO getUser(UserVO vo) {
		System.out.println("===> JDBC�� getUser() ó��");
		
		return mybatis.selectOne("UserMapper.getUser",vo);
	}
}