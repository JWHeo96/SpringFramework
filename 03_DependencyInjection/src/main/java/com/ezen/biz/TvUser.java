package com.ezen.biz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {
		// ������ �����̳ʸ� ����
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");

		// ������ �����̳ʿ��� �ʿ��� ��ü�� ��û(Lookup)
		TV tv = (TV)container.getBean("tv");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		container.close();
	}
}
