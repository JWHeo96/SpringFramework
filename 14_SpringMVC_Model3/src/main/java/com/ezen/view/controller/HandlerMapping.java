package com.ezen.view.controller;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	// ������
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		// ��� Controller ��ü ���
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());
	}
	
	// ��û URL�� ����, ������ ���� ó�� Controller�� ã�� ����
	public Controller getController(String path) {
		
		return mappings.get(path);
	}
}
