package com.ezen.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	// ȸ�� id�� �������� ȸ�� ��ȸ
	public MemberVO getMember(String id) {
		return mybatis.selectOne("MemeberMapper.getMember", id);
	}
	
	// ȸ�� ���� ���� ��ȸ
	public int confirmID(String userid) {
		if(mybatis.selectOne("MemeberMapper.confirmID",userid) != null)
			return 1;
		else
			return -1;
	}
	
	// ȸ�� ���
	public void insertMember(MemberVO memberVO) {
		mybatis.insert("insertMember", memberVO);
	}
}
