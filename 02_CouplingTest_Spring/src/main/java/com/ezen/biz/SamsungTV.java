package com.ezen.biz;

public class SamsungTV implements TV {
	
	public SamsungTV() {
		System.out.println("SamsungTV ��ü ����");
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
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV -- ������ ���ϴ�.");
	}

	@Override
	public void volumeUp() {
		System.out.println("SamsungTV -- �Ҹ��� �ø��ϴ�.");
	}

	@Override
	public void volumeDown() {
		System.out.println("SamsungTV -- �Ҹ��� �����ϴ�.");
	}

}
