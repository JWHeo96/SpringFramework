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
		System.out.println(method + "()메소드 수행에 걸린 시간:" + stopWatch.getTotalTimeMillis() + "(ms)초");
		
		return returnObj;
//		
//		System.out.println("[Before] 비즈니스 메소드 수행 전 작업");
//		
//		// 비즈니스 메소드 호출
//		// returnObj - 비즈니스 메소드를 호출하고 난 후 리턴값 저장
//		Object returnObj = pjp.proceed();
//		
//		System.out.println("[After] 비즈니스 메소드 수행 후 작업");
//		
//		return returnObj;
	}
}
