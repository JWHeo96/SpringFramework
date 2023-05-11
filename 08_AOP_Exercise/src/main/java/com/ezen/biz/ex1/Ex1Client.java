package com.ezen.biz.ex1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Ex1Client {

	private static Operation op;
	
	@Autowired
	public Ex1Client(Operation op) {
		this.op = op;
	}
	
	public static void main(String[] args) {
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		// Operation op = (Operation)container.getBean("opBean");

		System.out.println("msg ȣ�� >>>");
		op.message();

		System.out.println("m ȣ�� >>>");
		op.m();

		System.out.println("k ȣ�� >>>");
		op.k();

		System.out.println("���� ȣ�� >>>");
		op.throwException();

		container.close();
	}
}
