package com.ezen.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[Before] 비즈니스 메소드 수행 전 작업");
		
		// 비즈니스 메소드 호출
		// returnObj - 비즈니스 메소드를 호출하고 난 후 리턴값 저장
		Object returnObj = pjp.proceed();
		
		System.out.println("[After] 비즈니스 메소드 수행 후 작업");
		
		return returnObj;
	}
}
