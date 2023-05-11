package com.ezen.view.controller;

public class ViewResolver {

	public String prefix;	// ��θ�
	public String suffix;	// Ȯ����
	
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
	 * �Է� �Ķ���� :
	 * 		viewName - jsp ���ϸ�
	 */
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
	
}
