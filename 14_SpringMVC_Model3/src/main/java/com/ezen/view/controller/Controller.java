package com.ezen.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {

	// 사용자로부터 들어온 요청과 응답 처리
	public String handleRequest(HttpServletRequest request, HttpServletResponse response);
}
