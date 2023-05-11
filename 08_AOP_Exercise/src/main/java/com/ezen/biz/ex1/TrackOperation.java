package com.ezen.biz.ex1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class TrackOperation {

	public void myadvice(JoinPoint jp) {
		System.out.println("[사전 처리] 비즈니스 로직 수행 전 처리...");
	}
	
	public void myadvice2(JoinPoint jp) {
		System.out.println("[사후 처리] 비즈니스 로직 수행 후 처리...");
	}
	

	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[Before] 비즈니스 메소드 수행 전 작업");
		
		// 비즈니스 메소드 호출
		// returnObj - 비즈니스 메소드를 호출하고 난 후 리턴값 저장
		Object returnObj = pjp.proceed();
		
		System.out.println("[After] 비즈니스 메소드 수행 후 작업");
		
		return returnObj;
	}

}
