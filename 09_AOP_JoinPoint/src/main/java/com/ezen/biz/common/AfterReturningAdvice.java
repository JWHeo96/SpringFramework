package com.ezen.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterReturningAdvice {

	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		
		System.out.printf("[���� ó��] %s(), ���ϰ�: %s\n", method, returnObj.toString());
	}
}
