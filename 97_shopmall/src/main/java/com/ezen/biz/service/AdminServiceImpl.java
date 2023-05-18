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
	 * ������ ��й�ȣ ��ȸ
	 */
	@Override
	public int adminCheck(AdminVO vo) {
		String pwd_in_db = adminDao.adminCheck(vo.getId());
		
		if(pwd_in_db == null) {
			return -1;	// ������ ID�� �������� ����
		} else if (pwd_in_db.equals(vo.getPwd())) {
			return 1;	// �������� ������ ID
		} else {
			return 0;	// ��й�ȣ�� ��ġ���� ����
		}
	}

	/*
	 * ������ ���� ��ȸ
	 */
	@Override
	public AdminVO getAdmin(String id) {
		
		return adminDao.getAdmin(id);
	}

}
