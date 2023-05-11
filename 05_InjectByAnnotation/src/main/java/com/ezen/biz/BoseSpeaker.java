package com.ezen.biz;

import org.springframework.stereotype.Component;

@Component("bose")
public class BoseSpeaker implements Speaker {

	public BoseSpeaker() {
		System.out.println("==> Bose Speaker ��ü ����");
	}

	@Override
	public void volumeUp() {
		System.out.println("==> Bose Speaker : �Ҹ��� �ø��ϴ�.");
	}

	@Override
	public void volumeDown() {
		System.out.println("==> Bose Speaker : �Ҹ��� �����ϴ�.");
	}

}
