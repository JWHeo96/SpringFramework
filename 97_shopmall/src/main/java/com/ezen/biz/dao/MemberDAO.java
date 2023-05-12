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
	 * id�� �������� ����� ���� ��ȸ
	 */
	public MemberVO getMember(String id) {
		System.out.println(">>> ����� ���� ��ȸ");
		return mybatis.selectOne("MemberMapper.getMember", id);
	}
	
	// ȸ�� ���� ���� ��ȸ
	public int confirmID(String id) {
		System.out.println(">>> ȸ�� ���� ���� ��ȸ");
		String pwd = mybatis.selectOne("MemberMapper.confirmID",id);
		if (pwd != null)
			return 1;	// id ����
		else
			return -1;	
	}
	
	/*
	 * ȸ�� �α��� ����
	 * �Է� �Ķ����: vo - ȭ�� �Էµ�����
	 * ���ϰ�: ID�� �����ϰ� ��й�ȣ�� ������ 1
	 * 		��й�ȣ�� �ٸ��� 0
	 * 		ID�� �������� ������ -1�� ��ȯ
	 */
	public int loginID(MemberVO vo) {
		int result = -1;
		
		String pwd = mybatis.selectOne("MemberMapper.confirmID", vo);
		
		if (pwd == null) {
			result = -1;
		} else if (pwd.equals(vo.getPwd())){	// ���� �α���
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
	}
	
	// ȸ�� ���
	public void insertMember(MemberVO vo) {
		System.out.println(">>> ȸ�� ���� ó��");
		mybatis.insert("MemberMapper.insertMember", vo);
	}
	
	public List<AddressVO> selectAddressByDong(String dong) {
		return mybatis.selectList("MemberMapper.selectAddressByDong",dong);
	}
}
