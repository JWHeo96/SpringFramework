package com.ezen.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {	
	
	public void exceptionLog(JoinPoint jp, Exception execptObj) {
		String method = jp.getSignature().getName();
		
		System.out.println("[예외 처리]" + method + "() 메소드 수행 중 예외발생 : " + 
				execptObj.getMessage());
	}
}
