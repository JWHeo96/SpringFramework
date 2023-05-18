package com.ezen.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.AdminDAO;
import com.ezen.biz.dto.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;
	
	/*
	 * 관리자 비밀번호 조회
	 */
	@Override
	public int adminCheck(AdminVO vo) {
		String pwd_in_db = adminDao.adminCheck(vo.getId());
		
		if(pwd_in_db == null) {
			return -1;	// 관리자 ID가 존재하지 않음
		} else if (pwd_in_db.equals(vo.getPwd())) {
			return 1;	// 정상적인 관리자 ID
		} else {
			return 0;	// 비밀번호가 일치하지 않음
		}
	}

	/*
	 * 관리자 정보 조회
	 */
	@Override
	public AdminVO getAdmin(String id) {
		
		return adminDao.getAdmin(id);
	}

}
