package com.ezen.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {

	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[Before] ����Ͻ� �޼ҵ� ���� �� �۾�");
		
		// ����Ͻ� �޼ҵ� ȣ��
		// returnObj - ����Ͻ� �޼ҵ带 ȣ���ϰ� �� �� ���ϰ� ����
		Object returnObj = pjp.proceed();
		
		System.out.println("[After] ����Ͻ� �޼ҵ� ���� �� �۾�");
		
		return returnObj;
	}
}
