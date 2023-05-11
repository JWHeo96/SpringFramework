package com.ezen.biz;

public class BoseSpeaker implements Speaker {

	public BoseSpeaker() {
		System.out.println("==> Bose Speaker 객체 생성");
	}

	@Override
	public void volumeUp() {
		System.out.println("==> Bose Speaker : 소리를 올립니다.");
	}

	@Override
	public void volumeDown() {
		System.out.println("==> Bose Speaker : 소리를 내립니다.");
	}

}
