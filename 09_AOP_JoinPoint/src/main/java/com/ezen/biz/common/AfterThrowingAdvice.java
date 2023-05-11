package com.ezen.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice {	
	
	public void exceptionLog(JoinPoint jp, Exception execptObj) {
		String method = jp.getSignature().getName();
		
		System.out.println("[���� ó��]" + method + "() �޼ҵ� ���� �� ���ܹ߻� : " + 
				execptObj.getMessage());
	}
}
