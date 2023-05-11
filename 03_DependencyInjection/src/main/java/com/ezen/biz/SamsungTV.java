package com.ezen.biz;

public class SamsungTV implements TV {
	
	private BoseSpeaker speaker;
	private int price;

	public SamsungTV() {
		System.out.println("SamsungTV 객체 생성");
	}
	
	public SamsungTV(BoseSpeaker speaker) {
		this.speaker = speaker;
	}
	public SamsungTV(BoseSpeaker speaker, int price) {
		this.speaker = speaker;
		this.price = price;
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
		System.out.println(price);
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV -- 전원을 끕니다.");
	}

	@Override
	public void volumeUp() {
//		System.out.println("SamsungTV -- 소리를 올립니다.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
//		System.out.println("SamsungTV -- 소리를 내립니다.");
		speaker.volumeDown();
	}

}
