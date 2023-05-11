package com.ezen.biz.ex1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class TrackOperation {

	public void myadvice(JoinPoint jp) {
		System.out.println("[���� ó��] ����Ͻ� ���� ���� �� ó��...");
	}
	
	public void myadvice2(JoinPoint jp) {
		System.out.println("[���� ó��] ����Ͻ� ���� ���� �� ó��...");
	}
	

	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("[Before] ����Ͻ� �޼ҵ� ���� �� �۾�");
		
		// ����Ͻ� �޼ҵ� ȣ��
		// returnObj - ����Ͻ� �޼ҵ带 ȣ���ϰ� �� �� ���ϰ� ����
		Object returnObj = pjp.proceed();
		
		System.out.println("[After] ����Ͻ� �޼ҵ� ���� �� �۾�");
		
		return returnObj;
	}

}
