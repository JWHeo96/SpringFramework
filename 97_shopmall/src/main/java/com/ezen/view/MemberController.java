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
@SessionAttributes("loginUser")	// Model에 loginUser라는 객체가 저장될 때, Session에 loginUser 정보를 저장함
public class MemberController {

	@Autowired
	MemberService memberService;
	
	/*
	 * 약관화면 표시
	 */
	@GetMapping("/contract")
	public String contractView() {
		
		return "member/contract";
	}
	
	/*
	 * 회원가입 화면 이동 : POST 방식 전송
	 */
	@PostMapping("/join_form")
	public String joinView() {
		
		return "member/join";
	}
	
	/*
	 * 로그인 처리
	 */
	@PostMapping("/login")
	public String loginAction(MemberVO vo, Model model) {
		int result = memberService.loginID(vo);
		
		if (result == 1) {	// 로그인 성공
			model.addAttribute("loginUser",memberService.getMember(vo.getId()));
			
			return "redirect:index";
		} else {
			return "member/login_fail";
		}
	}
	
	/*
	 * 로그아웃 처리
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "member/login";
	}
	
	/*
	 * 로그인 화면 표시
	 */
	@GetMapping("/login_form")
	public String loginView() {
		return "member/login";
	}
	@PostMapping("/id_check_form")
	public String idCheckAction(MemberVO vo, Model model) {
		// id 중복확인 조회
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message",result);
		model.addAttribute("id",vo.getId());
		
		return "member/idcheck";
	}
	
	/*
	 * ID 중복체크 화면 표시 및 결과 전송
	 */
	@GetMapping("/id_check_form")
	public String idCheckView(MemberVO vo, Model model) {
		// id 중복확인 조회
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message",result);
		model.addAttribute("id",vo.getId());
		
		return "member/idcheck";
	}
	
	/*
	 * 회원가입 처리
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
	 * 동 이름으로 주소 찾기
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
