package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	// ȸ�� id�� �������� ȸ�� ��ȸ
	MemberVO getMember(String id);

	// ��ü ȸ�� ��ȸ
	List<MemberVO> getMemberList();
	
	// ȸ�� ���
	void insertMember(MemberVO memberVO);

}