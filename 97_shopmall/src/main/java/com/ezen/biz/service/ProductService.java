package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

public interface ProductService {

	// �Ż�ǰ ��� ������
	List<ProductVO> getNewProductList();

	// ����Ʈ ��ǰ ������
	List<ProductVO> getBestProductList();

	// ��ǰ��ȣ�� �ϳ��� ��ǰ���� ������
	ProductVO getProduct(ProductVO vo);

	// ��ǰ ������ ��ǰ ��� ������
	List<ProductVO> getProductListByKind(String kind);

}