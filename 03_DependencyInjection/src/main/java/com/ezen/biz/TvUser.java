package com.ezen.biz;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUser {

	public static void main(String[] args) {
		// 스프링 컨테이너를 구동
		AbstractApplicationContext container =
				new GenericXmlApplicationContext("applicationContext.xml");

		// 스프링 컨테이너에게 필요한 객체를 요청(Lookup)
		TV tv = (TV)container.getBean("tv");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		container.close();
	}
}
