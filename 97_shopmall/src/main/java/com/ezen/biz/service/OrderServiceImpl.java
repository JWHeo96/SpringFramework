package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.OrderDAO;
import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private CartService cartService;
	
	@Override
	public int selectMaxOseq() {
		return orderDao.selectMaxOseq();
	}

	@Override
	public int insertOrder(OrderVO vo) {
		// �ű� �ֹ� ��ȣ�� ����
		int oseq = selectMaxOseq();
		vo.setOseq(oseq);
		
		// (2) �ű��ֹ��� �ֹ����̺� ����
		orderDao.insertOrder(vo);
		
		// (3) ��ٱ���
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		for (CartVO  cart : cartList) {
			OrderVO order = new OrderVO();
			
			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());
			
			insertOrderDetail(order);
			
			// ��ٱ��� ���̺� ������Ʈ(�ֹ�ó�� ��� ������Ʈ)
			cartService.updateCart(cart.getCseq());
		}
		
		return oseq;
	}
	
	@Override
	public void insertOrderDetail(OrderVO vo) {
		
		orderDao.insertOrderDetail(vo);
	}

}
