package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.OrderVO;

public interface OrderService {

	int selectMaxOseq();

	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);

	List<OrderVO> getListOrderById(OrderVO vo);
	
	public List<Integer> getSelectSeqOrdering(OrderVO vo);
}