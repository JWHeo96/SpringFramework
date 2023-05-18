package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

public interface ProductService {

	// �Ż�ǰ ��� ������
	List<ProductVO> getNewProductList();

	// ����Ʈ ��ǰ ������
	List<ProductVO> getBestProductList();

	// ��ǰ��ȣ�� �ϳ��� ��ǰ���� ������
	ProductVO getProduct(ProductVO vo);

	// ��ǰ ������ ��ǰ ��� ������
	List<ProductVO> getProductListByKind(String kind);

	
	// �� ��ǰ ����� ���� ��ȸ
	int countProductList(String name); 
	
	// ��ǰ ��� ��ȸ
	List<ProductVO> getListProduct(String name);
	
	// �������� ��ǰ ��� ��ȸ
	List<ProductVO> getListProductWithPaging(Criteria criteria, String name);
	
	// ��ǰ �߰�
	void insertProduct(ProductVO vo);
	
	// ��ǰ ����
	void updateProduct(ProductVO vo); 
}