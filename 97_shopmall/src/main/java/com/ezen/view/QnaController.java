package com.ezen.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	/*
	 * �α��� ������ QnA ��� ��ȸ
	 */
	@GetMapping("/qna_list")
	public String qnaList(HttpSession session, Model model) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			// ����� ���̵� �������� Qna ��� ��ȸ
			List<QnaVO> qnaList = qnaService.getListQna(loginUser.getId());
			
			// ȭ�鿡 ������ Qna ��� ����
			model.addAttribute("qnaList", qnaList);
			
			// QnaList ȭ�� ǥ��
			return "qna/qnaList";
		}
	}
	
	@GetMapping("/qna_write_form")
	public String insertQnaView(HttpSession session) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");

		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) 
			return "member/login";
		else
			return "qna/qnaWrite";
	}
	
	@PostMapping("/qna_write")
	public String qnaWriteAction(QnaVO vo, HttpSession session) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			vo.setId(loginUser.getId());
			qnaService.insertQna(vo);
			return "redirect:qna_list";
		}
	}
	
	@GetMapping("/qna_view")
	public String qnaView(QnaVO vo, HttpSession session, Model model) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			QnaVO qnaView = qnaService.getQna(vo.getQseq());
			
			model.addAttribute("qnaVO", qnaView);
		}
		return "qna/qnaView";
	}
}
