package com.ezen.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.biz.dao.UserDAO;
import com.ezen.biz.dto.UserVO;
import com.ezen.biz.service.UserService;

@Controller
@SessionAttributes("loginUser")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(@ModelAttribute("user") UserVO vo) {
		System.out.println("�α��� ȭ������ �̵�");
		
		vo.setId("heo");
		vo.setPassword("1234");
		
		return "login.jsp";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserDAO userDAO, UserVO vo, HttpSession session, Model model) throws IllegalAccessException {
		System.out.println("�α��� ó��");
		System.out.println();
		
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("���̵�� �ݵ�� �Է��ؾ� �մϴ�.");
		}
		
		UserVO user = userService.getUser(vo);
		
		if (user != null){	// ����� ���� Ȯ��
			session.setAttribute("userName", user.getName());
			model.addAttribute("loginUser", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		} 
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("�α׾ƿ� ó��");
		// 1. �������� ����� ���� ��ü�� ���� �����Ѵ�.
		session.invalidate();
		
		// 2. ���� ���� ��, ���� ȭ������ �̵��Ѵ�.
		return "login.do";
	}

}