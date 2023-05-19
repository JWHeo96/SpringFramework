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
		// (1) 신규 주문 번호를 생성
		int oseq = selectMaxOseq();
		vo.setOseq(oseq);
		
		// (2) 신규주문을 주문테이블에 저장
		orderDao.insertOrder(vo);
		
		// (3) 장바구니 목록을 읽어 order_detail 테이블에 저장
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		for (CartVO  cart : cartList) {
			OrderVO order = new OrderVO();
			
			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());
			
			insertOrderDetail(order);
			/*
			if (true)
				throw new IllegalArgumentException("장바구니 데이터를 수정할 수 없습니다.");
			 */
			
			// 장바구니 테이블 업데이트(주문처리 결과 업데이트)
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
