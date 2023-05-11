package com.ezen.view.controller;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	// 생성자
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		
		// 모든 Controller 객체 등록
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());
	}
	
	// 요청 URL에 대해, 적절한 업무 처리 Controller를 찾아 전달
	public Controller getController(String path) {
		
		return mappings.get(path);
	}
}
