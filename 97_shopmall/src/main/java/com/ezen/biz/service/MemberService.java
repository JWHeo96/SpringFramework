package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	/*
	 * id�� �������� ����� ���� ��ȸ
	 */
	MemberVO getMember(String id);

	// ȸ�� ���� ���� ��ȸ
	int confirmID(String id);
	
	int loginID(MemberVO vo);

	// ȸ�� ���
	void insertMember(MemberVO vo);

	public List<AddressVO> selectAddressByDong(String dong);
}