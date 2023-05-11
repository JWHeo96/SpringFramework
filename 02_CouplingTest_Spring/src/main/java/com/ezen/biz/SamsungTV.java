package com.ezen.biz;

public class SamsungTV implements TV {
	
	public SamsungTV() {
		System.out.println("SamsungTV 객체 생성");
	}
	
	public void initMethod() {
		System.out.println("객체 생성 전 초기화 작업 처리...");
	}
	
	public void destroyMethod() {
		System.out.println("객체 삭제 전 처리할 작업...");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV -- 전원을 켭니다.");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV -- 전원을 끕니다.");
	}

	@Override
	public void volumeUp() {
		System.out.println("SamsungTV -- 소리를 올립니다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("SamsungTV -- 소리를 내립니다.");
	}

}
