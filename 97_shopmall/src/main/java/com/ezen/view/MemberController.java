package com.ezen.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.service.MemberService;

@Controller
@SessionAttributes("loginUser")	// Model�� loginUser��� ��ü�� ����� ��, Session�� loginUser ������ ������
public class MemberController {

	@Autowired
	MemberService memberService;
	
	/*
	 * ���ȭ�� ǥ��
	 */
	@GetMapping("/contract")
	public String contractView() {
		
		return "member/contract";
	}
	
	/*
	 * ȸ������ ȭ�� �̵� : POST ��� ����
	 */
	@PostMapping("/join_form")
	public String joinView() {
		
		return "member/join";
	}
	
	/*
	 * �α��� ó��
	 */
	@PostMapping("/login")
	public String loginAction(MemberVO vo, Model model) {
		int result = memberService.loginID(vo);
		
		if (result == 1) {	// �α��� ����
			model.addAttribute("loginUser",memberService.getMember(vo.getId()));
			
			return "redirect:index";
		} else {
			return "member/login_fail";
		}
	}
	
	/*
	 * �α׾ƿ� ó��
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "member/login";
	}
	
	/*
	 * �α��� ȭ�� ǥ��
	 */
	@GetMapping("/login_form")
	public String loginView() {
		return "member/login";
	}
	@PostMapping("/id_check_form")
	public String idCheckAction(MemberVO vo, Model model) {
		// id �ߺ�Ȯ�� ��ȸ
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message",result);
		model.addAttribute("id",vo.getId());
		
		return "member/idcheck";
	}
	
	/*
	 * ID �ߺ�üũ ȭ�� ǥ�� �� ��� ����
	 */
	@GetMapping("/id_check_form")
	public String idCheckView(MemberVO vo, Model model) {
		// id �ߺ�Ȯ�� ��ȸ
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message",result);
		model.addAttribute("id",vo.getId());
		
		return "member/idcheck";
	}
	
	/*
	 * ȸ������ ó��
	 */
	@PostMapping("/join")
	public String joinAction(MemberVO vo,
			@RequestParam(value="addr1", defaultValue="") String addr1,
			@RequestParam(value="addr2", defaultValue="") String addr2) {
		
		vo.setAddress(addr1 + " " + addr2);
		memberService.insertMember(vo);
		
		return "member/login";
	}
	
	/*
	 * �� �̸����� �ּ� ã��
	 */
	@GetMapping("/find_zip_num")
	public String findZipNumView() {
		
		return "member/findZipNum";
	}
	
	@PostMapping("/find_zip_num")
	public String findZipNumAction(AddressVO vo,Model model) {
		
		List<AddressVO> addressList = memberService.selectAddressByDong(vo.getDong());
		model.addAttribute("addressList",addressList);
		
		return "member/findZipNum";
	}

}
