package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	// 회원 id를 조건으로 회원 조회
	MemberVO getMember(String id);

	// 전체 회원 조회
	List<MemberVO> getMemberList();
	
	// 회원 등록
	void insertMember(MemberVO memberVO);

}