package com.ezen.biz.board;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.biz.dto.UserVO;
import com.ezen.biz.service.UserService;

public class UserServiceClient {
	private static UserService userService;

	public static void main(String[] args) {
		// 1. ������ �����̳� ����
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. UserSerivceImpl ��ü Lookup
		userService = (UserService) container.getBean("userService");
		
		// 3. �α��� ��� �׽�Ʈ
		// UserVO ��ü ����, id/password ����
		UserVO user = new UserVO();
		user.setId("kim");
		user.setPassword("1234");
		
		// userService�� getUser() ȣ��
		UserVO result = userService.getUser(user);
		
		// ���� ȣ�� ����� �̿��Ͽ� �α��� �������� Ȯ��
		if (result != null) {
			System.out.println("�α��� ����!");
		} else {
			System.out.println("�α��� ����...");
		}
		
		// 5. �����̳� ����
		container.close();
	}

}
