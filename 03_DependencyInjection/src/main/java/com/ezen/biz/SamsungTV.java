package com.ezen.biz;

public class SamsungTV implements TV {
	
	private BoseSpeaker speaker;
	private int price;

	public SamsungTV() {
		System.out.println("SamsungTV ��ü ����");
	}
	
	public SamsungTV(BoseSpeaker speaker) {
		this.speaker = speaker;
	}
	public SamsungTV(BoseSpeaker speaker, int price) {
		this.speaker = speaker;
		this.price = price;
	}
	
	public void initMethod() {
		System.out.println("��ü ���� �� �ʱ�ȭ �۾� ó��...");
	}
	
	public void destroyMethod() {
		System.out.println("��ü ���� �� ó���� �۾�...");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamsungTV -- ������ �մϴ�.");
		System.out.println(price);
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV -- ������ ���ϴ�.");
	}

	@Override
	public void volumeUp() {
//		System.out.println("SamsungTV -- �Ҹ��� �ø��ϴ�.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
//		System.out.println("SamsungTV -- �Ҹ��� �����ϴ�.");
		speaker.volumeDown();
	}

}
