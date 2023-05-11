package com.ezen.biz;

public class SamsungTV implements TV {

	private Speaker speaker;
	private int price;

	// Constructor
	public SamsungTV() {
		System.out.println("SamsungTV 객체 생성");
	}

	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
	}

	public SamsungTV(Speaker speaker, int price) {
		this.speaker = speaker;
		this.price = price;
	}
	
	// Setter
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// Init & Destroy Method
	public void initMethod() {
		System.out.println("객체 생성 전 초기화 작업 처리...");
	}

	public void destroyMethod() {
		System.out.println("객체 삭제 전 처리할 작업...");
	}

	// Interface Override
	@Override
	public void powerOn() {
		System.out.println("SamsungTV -- 전원을 켭니다.");
		System.out.println("가격은 " + price + "원");
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
