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
		// �α����� �Ǿ� �ִ��� Ȯ�� 
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
		// �α����� �Ǿ� ���� ���
		// ȸ�� ID�� �����ϰ� ��ٱ��Ͽ� insert ����
			vo.setId(loginUser.getId());
			cartService.insertCart(vo);
			
			return "redirect:cart_list";
		}
	}
	
	@GetMapping("/cart_list")
	public String cartList(HttpSession session, Model model) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
		// �α����� �Ǿ� ������ cartService�� ��ٱ��� ��� ����
			List<CartVO> cartList = (ArrayList<CartVO>)cartService.listCart(loginUser.getId());
		
			// ��ٱ��� �Ѿ� ���
			int totalAmount = 0;
			for (CartVO vo: cartList) {
				totalAmount += vo.getPrice2() * vo.getQuantity();
			}
			
			// Model ��ü�� ������ �����Ͽ� jsp�� ����
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
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			// OrderVO ��ü�� id�� ����
			order.setId(loginUser.getId());
			
			orderService.insertOrder(order);
			return "cart_List";
		}
	}
}
