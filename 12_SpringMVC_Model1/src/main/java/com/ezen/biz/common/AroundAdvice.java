package com.ezen.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {

//	@Pointcut("execution(* com.ezen.biz..*Impl..*(..))")
//	public void allPointcut() {}
	
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object returnObj = pjp.proceed();
		stopWatch.stop();
		System.out.println(method + "()�޼ҵ� ���࿡ �ɸ� �ð�:" + stopWatch.getTotalTimeMillis() + "(ms)��");
		
		return returnObj;
//		
//		System.out.println("[Before] ����Ͻ� �޼ҵ� ���� �� �۾�");
//		
//		// ����Ͻ� �޼ҵ� ȣ��
//		// returnObj - ����Ͻ� �޼ҵ带 ȣ���ϰ� �� �� ���ϰ� ����
//		Object returnObj = pjp.proceed();
//		
//		System.out.println("[After] ����Ͻ� �޼ҵ� ���� �� �۾�");
//		
//		return returnObj;
	}
}
