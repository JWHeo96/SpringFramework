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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/*
	 * ��ٱ��Ͽ��� �ֹ�ó���� �ϱ� ���� �޼ҵ�
	 */
	@PostMapping("/order_insert")
	public String orderInsert(HttpSession session, OrderVO order, RedirectAttributes reAttr) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			// OrderVO ��ü�� id�� ����
			order.setId(loginUser.getId());
			
			int oseq = orderService.insertOrder(order);
			
			// �ֹ�Ȯ�� ȭ���� ǥ���ϱ� ���� �ֹ���ȣ(oseq) ����
			// addAttribute�� GET����̸� ���ΰ�ħ�� �ص� �����Ǵ� ������ ����
			// addFlashAttribute�� POST����̸� ���ΰ�ħ�� �ص� �������� �ʴ´�.
			reAttr.addAttribute("oseq", oseq);
			
			return "redirect:order_list";
		}
	}

	/*
	 * �ֹ�ó�� ȭ�� ǥ��
	 */
	@GetMapping("/order_list")
	public String orderListView(HttpSession session, OrderVO vo, Model model) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			// oseq�� insert_order���� ���޵� �����Ͱ� 
			// command ��ü�� ����Ǿ� ����
			vo.setId(loginUser.getId());
			vo.setResult("1");	// ó����� : '��ó��'�� ��ȸ
			
			// �α����� ȸ���� ��� ���� ó���� �ֹ� ������ ��ȸ
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
			// �ֹ� �Ѿ� ���
			int totalAmount = 0;
			for(OrderVO order : orderList) {
				totalAmount += order.getQuantity() * order.getPrice2();
			}
			
			// ȭ�鿡 ǥ���� ������ ����
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);
			
			return "mypage/orderList";
		}
	}
	
	/*
	 * �������� �ֹ� ���� ��û ó��
	 */
	@GetMapping("/mypage")
	public String myPageView(HttpSession session, OrderVO vo, Model model) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			
			// (1) �������� �ֹ���ȣ ��� ��ȸ
			vo.setId(loginUser.getId());
			vo.setResult("1");
			List<Integer> oseqList = orderService.getSelectSeqOrdering(vo);
			
			// (2) ���� �� �ֹ���ȣ�� ���� �ֹ������� ��ȸ�ϰ�
			//     ��� ������ �����Ѵ�.
			List<OrderVO> summaryList = new ArrayList<>();
			for (int oseq : oseqList) {
				// (2-1) �� �ֹ���ȣ�� �ֹ����� ��ȸ
				OrderVO order = new OrderVO();
				
				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult("1");
				
				// �ֹ���ȣ�� �ֹ����
				List<OrderVO> orderList = orderService.getListOrderById(order);
				
				// (2-2) �� �ֹ���� ���� ����
				OrderVO summary = new OrderVO(); // ������� ���� ����
				summary.setOseq(orderList.get(0).getOseq()); // ù��° ��� �ֹ���ȣ ����
				summary.setIndate(orderList.get(0).getIndate()); // ù��° ��� �ֹ����� ����
				
				// ��ǰ�� ������� ����
				if (orderList.size() >= 2) {
					summary.setPname(orderList.get(0).getPname() + " �� " + (orderList.size()-1) + " ��");
				} else { // �ֹ��� ���Ե� ��ǰ�� 1����.
					summary.setPname(orderList.get(0).getPname());
				}
				
				// �� �ֹ��� �հ�ݾ�
				int amount = 0;
				
				for (OrderVO item : orderList) {
					amount += item.getQuantity() * item.getPrice2();
				}
				summary.setPrice2(amount); // �հ�ݾ� ����
				
				// �� �ֹ���� ������ ����Ʈ�� �߰�
				summaryList.add(summary);
			}
			
			// (3) ȭ�鿡 ������ �����͸� ����
			model.addAttribute("orderList", summaryList);
			model.addAttribute("title", "�������� �ֹ�����");
		}
	return "mypage/mypage";
}
	
	/*
	 * �ֹ� �� ���� ǥ�� ó��
	 */
	@GetMapping("/order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			// (1) �ֹ���ȣ�� ���� �ֹ���� ��ȸ
			vo.setId(loginUser.getId());
			vo.setResult("");	// ó�����: ��ó��, ó�� ��� ��ȸ
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
			// (2) �ֹ��� ���� ����
			OrderVO orderDetail = new OrderVO();
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			
			// (3) �ֹ� �Ѿ� ���
			int totalAmount = 0;
			for (int i=0; i<orderList.size(); i++) {
				totalAmount += orderList.get(i).getQuantity() * orderList.get(i).getPrice2();
			}
			
			// (4) ȭ�鿡 ǥ���� ���� ����
			model.addAttribute("title", "My Page(�ֹ� �� ����)");
			model.addAttribute("orderList", orderList);
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", totalAmount);
			
			// (5) mypage ȭ�� ȣ��
			return "mypage/orderDetail";
		}
	}
	
	/*
	 * ��ü �ֹ�����(ó��, ��ó�� ����)
	 */
	@GetMapping("/order_all")
	public String orderAllView(OrderVO vo, HttpSession session, Model model) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "member/login";
		} else {
			// (1) �������� �ֹ���ȣ ��� ��ȸ
			vo.setId(loginUser.getId());
			vo.setResult(""); // ��� ó����� ��ȸ
			List<Integer> oseqList = orderService.getSelectSeqOrdering(vo);
			
			// (2) ���� �� �ֹ���ȣ�� ���� �ֹ������� ��ȸ�ϰ�
			//     ��� ������ �����Ѵ�.
			List<OrderVO> summaryList = new ArrayList<>();
			for (int oseq : oseqList) {
				// (2-1) �� �ֹ���ȣ�� �ֹ����� ��ȸ
				OrderVO order = new OrderVO();
				
				order.setId(loginUser.getId());
				order.setOseq(oseq);
				order.setResult("");
				
				// �ֹ���ȣ�� �ֹ����
				List<OrderVO> orderList = orderService.getListOrderById(order);
				
				// (2-2) �� �ֹ���� ���� ����
				OrderVO summary = new OrderVO(); // ������� ���� ����
				summary.setOseq(orderList.get(0).getOseq()); // ù��° ��� �ֹ���ȣ ����
				summary.setIndate(orderList.get(0).getIndate()); // ù��° ��� �ֹ����� ����
				
				// ��ǰ�� ������� ����
				if (orderList.size() >= 2) {
					summary.setPname(orderList.get(0).getPname() + " �� " + (orderList.size()-1) + " ��");
				} else { // �ֹ��� ���Ե� ��ǰ�� 1����.
					summary.setPname(orderList.get(0).getPname());
				}
				
				// �� �ֹ��� �հ�ݾ�
				int amount = 0;
				
				for (OrderVO item : orderList) {
					amount += item.getQuantity() * item.getPrice2();
				}
				summary.setPrice2(amount); // �հ�ݾ� ����
				
				// �� �ֹ���� ������ ����Ʈ�� �߰�
				summaryList.add(summary);
			}
			
			// (3) ȭ�鿡 ������ �����͸� ����
			model.addAttribute("orderList", summaryList);
			model.addAttribute("title", "�� �ֹ�����");
		}
		return "mypage/mypage";
	}
}
