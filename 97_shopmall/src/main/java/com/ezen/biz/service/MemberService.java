package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	/*
	 * id를 조건으로 사용자 정보 조회
	 */
	MemberVO getMember(String id);

	// 회원 존재 여부 조회
	int confirmID(String id);
	
	int loginID(MemberVO vo);

	// 회원 등록
	void insertMember(MemberVO vo);

	List<AddressVO> selectAddressByDong(String dong);
	
	List<MemberVO> listMember(String name);
	
	String selectIdByNameEmail(MemberVO vo);
	
	String selectPwdByIdNameEmail(MemberVO vo);
	
	void changePwd(MemberVO vo);
}