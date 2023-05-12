package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	/*
	 * id를 조건으로 사용자 정보 조회
	 */
	public MemberVO getMember(String id) {
		System.out.println(">>> 사용자 정보 조회");
		return mybatis.selectOne("MemberMapper.getMember", id);
	}
	
	// 회원 존재 여부 조회
	public int confirmID(String id) {
		System.out.println(">>> 회원 존재 여부 조회");
		String pwd = mybatis.selectOne("MemberMapper.confirmID",id);
		if (pwd != null)
			return 1;	// id 존재
		else
			return -1;	
	}
	
	/*
	 * 회원 로그인 인증
	 * 입력 파라미터: vo - 화면 입력데이터
	 * 리턴값: ID가 존재하고 비밀번호가 같으면 1
	 * 		비밀번호가 다르면 0
	 * 		ID가 존재하지 않으면 -1을 반환
	 */
	public int loginID(MemberVO vo) {
		int result = -1;
		
		String pwd = mybatis.selectOne("MemberMapper.confirmID", vo);
		
		if (pwd == null) {
			result = -1;
		} else if (pwd.equals(vo.getPwd())){	// 정상 로그인
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
	}
	
	// 회원 등록
	public void insertMember(MemberVO vo) {
		System.out.println(">>> 회원 가입 처리");
		mybatis.insert("MemberMapper.insertMember", vo);
	}
	
	public List<AddressVO> selectAddressByDong(String dong) {
		return mybatis.selectList("MemberMapper.selectAddressByDong",dong);
	}
}
