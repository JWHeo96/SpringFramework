package com.ezen.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.service.CartService;
import com.ezen.biz.service.OrderService;

@Controller
public class MypageController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/cart_insert")
	public String cartInsert(CartVO vo, HttpSession session) {
		// 로그인이 되어 있는지 확인 
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		// 로그인이 안되어 있는 경우 로그인 페이지로 이동
		if (loginUser == null) {
			return "member/login";
		} else {
		// 로그인이 되어 있을 경우
		// 회원 ID를 설정하고 장바구니에 insert 수행
			vo.setId(loginUser.getId());
			cartService.insertCart(vo);
			
			return "redirect:cart_list";
		}
	}
	
	@GetMapping("/cart_list")
	public String cartList(HttpSession session, Model model) {
		// 로그인이 되어 있는지 확인
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// 로그인이 안되어 있는 경우 로그인 페이지로 이동
		if (loginUser == null) {
			return "member/login";
		} else {
		// 로그인이 되어 있으면 cartService의 장바구니 목록 실행
			List<CartVO> cartList = (ArrayList<CartVO>)cartService.listCart(loginUser.getId());
		
			// 장바구니 총액 계산
			int totalAmount = 0;
			for (CartVO vo: cartList) {
				totalAmount += vo.getPrice2() * vo.getQuantity();
			}
			
			// Model 객체에 데이터 저장하여 jsp에 전달
			model.addAttribute("cartList", cartList);
			
			model.addAttribute("totalPrice",totalAmount);
			
			return "mypage/cartList";
		}
	}
	
	@PostMapping("cart_delete")
	public String cartDelete(@RequestParam(value="cseq") int[] cseq) {

		for(int i=0; i<cseq.length; i++) {
			System.out.println(cseq[i]);
			cartService.deleteCart(cseq[i]);
		}
		
		return "redirect:cart_list";
	}
	
	@PostMapping("/order_insert")
	public String orderInsert(HttpSession session, OrderVO order) {
		// 로그인이 되어 있는지 확인
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// 로그인이 안되어 있는 경우 로그인 페이지로 이동
		if (loginUser == null) {
			return "member/login";
		} else {
			// OrderVO 객체에 id를 저장
			order.setId(loginUser.getId());
			
			orderService.insertOrder(order);
			return "cart_List";
		}
	}
}
