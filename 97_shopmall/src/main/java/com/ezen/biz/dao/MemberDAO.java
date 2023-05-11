package com.ezen.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	// 회원 id를 조건으로 회원 조회
	public MemberVO getMember(String id) {
		return mybatis.selectOne("MemeberMapper.getMember", id);
	}
	
	// 회원 존재 여부 조회
	public int confirmID(String userid) {
		if(mybatis.selectOne("MemeberMapper.confirmID",userid) != null)
			return 1;
		else
			return -1;
	}
	
	// 회원 등록
	public void insertMember(MemberVO memberVO) {
		mybatis.insert("insertMember", memberVO);
	}
}
