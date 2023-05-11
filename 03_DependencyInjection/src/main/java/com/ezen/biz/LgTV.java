package com.ezen.biz;

public class LgTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("LgTV -- 전원을 켭니다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV -- 전원을 끕니다.");
	}

	@Override
	public void volumeUp() {
		System.out.println("LgTV -- 소리를 올립니다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("LgTV -- 소리를 내립니다.");
	}

}
