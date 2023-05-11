package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dto.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberService memberService;
	
	@Override
	public MemberVO getMember(String id) {
		return memberService.getMember(id);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberService.getMemberList();
	}

	@Override
	public void insertMember(MemberVO memberVO) {
		memberService.insertMember(memberVO);
	}

}
