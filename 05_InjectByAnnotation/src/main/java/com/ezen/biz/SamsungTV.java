package com.ezen.biz;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component("tv")
public class SamsungTV implements TV {

	// @Autowired // ������ Ÿ���� ��ü�� �����̳ʿ��� ã�� ������ ���� ��û
	// @Qualifier("jbl") // 2�� �̻��� ��ü�� Ư�� ��ü ����
	@Resource(name = "jbl")	// Autowired/Qualifier�� ����
	private Speaker speaker;
	private int price;

	// Constructor
	public SamsungTV() {
		System.out.println("SamsungTV ��ü ����");
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
		System.out.println("��ü ���� �� �ʱ�ȭ �۾� ó��...");
	}

	public void destroyMethod() {
		System.out.println("��ü ���� �� ó���� �۾�...");
	}

	// Interface Override
	@Override
	public void powerOn() {
		System.out.println("SamsungTV -- ������ �մϴ�.");
		System.out.println("������ " + price + "��");
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
