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
		// (1) �ű� �ֹ� ��ȣ�� ����
		int oseq = selectMaxOseq();
		vo.setOseq(oseq);
		
		// (2) �ű��ֹ��� �ֹ����̺� ����
		orderDao.insertOrder(vo);
		
		// (3) ��ٱ��� ����� �о� order_detail ���̺� ����
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		for (CartVO  cart : cartList) {
			OrderVO order = new OrderVO();
			
			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());
			
			insertOrderDetail(order);
			/*
			if (true)
				throw new IllegalArgumentException("��ٱ��� �����͸� ������ �� �����ϴ�.");
			 */
			
			// ��ٱ��� ���̺� ������Ʈ(�ֹ�ó�� ��� ������Ʈ)
			cartService.updateCart(cart.getCseq());
		}
		
		return oseq;
	}
	
	@Override
	public void insertOrderDetail(OrderVO vo) {
		
		orderDao.insertOrderDetail(vo);
	}

	@Override
	public List<OrderVO> getListOrderById(OrderVO vo) {
		
		return orderDao.listOrderById(vo);
	}

	@Override
	public List<Integer> getSelectSeqOrdering(OrderVO vo) {

		return orderDao.getSelectSeqOrdering(vo);
	}

	@Override
	public List<OrderVO> getListOrder(String mname) {
		
		return orderDao.listOrder(mname);
	}

	@Override
	public void updateOrderResult(int odseq) {
		
		orderDao.updateOrderResult(odseq);
	}

}
