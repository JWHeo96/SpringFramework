package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.MemberDAO;
import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;
	
	@Override
	public MemberVO getMember(String id) {
		
		return memberDao.getMember(id);
	}
	
	@Override
	public int confirmID(String id) {
	
		return memberDao.confirmID(id);
	}

	@Override
	public void insertMember(MemberVO vo) {
		
		memberDao.insertMember(vo);
	}

	@Override
	public List<AddressVO> selectAddressByDong(String dong) {
		return memberDao.selectAddressByDong(dong);
	}

	@Override
	public int loginID(MemberVO vo) {
		return memberDao.loginID(vo);
	}

}
