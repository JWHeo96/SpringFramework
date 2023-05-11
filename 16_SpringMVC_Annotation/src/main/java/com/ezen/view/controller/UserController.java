package com.ezen.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.biz.dao.UserDAO;
import com.ezen.biz.dto.UserVO;

@Controller
@SessionAttributes("loginUser")
public class UserController {

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {
		System.out.println("로그인 화면으로 이동");
		
		vo.setId("heo");
		vo.setPassword("1234");
		
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserDAO userDAO, UserVO vo, HttpSession session, Model model) throws IllegalAccessException {
		System.out.println("로그인 처리");
		
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		
		UserVO user = userDAO.getUser(vo);

		if (user != null){	// 사용자 인증 확인
			session.setAttribute("userName", user.getName());
			model.addAttribute("loginUser", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		} 
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 처리");
		// 1. 브라우저와 연결된 세션 객체를 강제 종료한다.
		session.invalidate();
		
		// 2. 세션 종료 후, 메인 화면으로 이동한다.
		return "login.do";
	}

}
