package com.ezen.biz.common;

/*
 * 비즈니스 로직 처리 중간에 처리정보를 출력해 주는 클래스
 */
public class LogAdvice {
	public void printLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 작업...");
	}
}
