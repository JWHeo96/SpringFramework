package com.ezen.view.controller;

public class ViewResolver {

	public String prefix;	// 경로명
	public String suffix;	// 확장자
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	/*
	 * 입력 파라미터 :
	 * 		viewName - jsp 파일명
	 */
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
	
}
