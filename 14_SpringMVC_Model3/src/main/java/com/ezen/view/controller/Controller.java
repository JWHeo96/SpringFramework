package com.ezen.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

	// ����ڷκ��� ���� ��û�� ���� ó��
	public String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
