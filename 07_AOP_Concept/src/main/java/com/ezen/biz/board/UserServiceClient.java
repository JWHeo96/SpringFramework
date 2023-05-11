package com.ezen.biz.board;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.ezen.biz.dto.UserVO;
import com.ezen.biz.service.UserService;

public class UserServiceClient {
	private static UserService userService;

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 구동
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. UserSerivceImpl 객체 Lookup
		userService = (UserService) container.getBean("userService");
		
		// 3. 로그인 기능 테스트
		// UserVO 객체 생성, id/password 설정
		UserVO user = new UserVO();
		user.setId("kim");
		user.setPassword("1234");
		
		// userService의 getUser() 호출
		UserVO result = userService.getUser(user);
		
		// 위의 호출 결과를 이용하여 로그인 성공여부 확인
		if (result != null) {
			System.out.println("로그인 성공!");
		} else {
			System.out.println("로그인 실패...");
		}
		
		// 5. 컨테이너 종료
		container.close();
	}

}
