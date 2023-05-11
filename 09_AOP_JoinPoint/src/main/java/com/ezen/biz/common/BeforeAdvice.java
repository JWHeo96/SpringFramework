package com.ezen.biz.common;

import org.aspectj.lang.JoinPoint;

public class BeforeAdvice {
	
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		// �Ǵ�
		// Signature sig = jp.getSignature();
		// String method = sig.getName();
		if (args.length == 0) {
			System.out.println("[���� ó��] ����Ͻ� ���� ������ �۾�...");
			System.out.println("[���� ó��]" + method + "() ARGS: ����\n");
		} else {
			System.out.println("[���� ó��] ����Ͻ� ���� ������ �۾�...");
			System.out.println("[���� ó��]" + method + "() ARGS: " + args[0].toString() + "\n");
		}
	}
}
