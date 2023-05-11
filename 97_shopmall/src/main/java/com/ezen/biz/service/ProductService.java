package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

public interface ProductService {

	// 신상품 목록 얻어오기
	List<ProductVO> getNewProductList();

	// 베스트 상품 얻어오기
	List<ProductVO> getBestProductList();

	// 상품번호로 하나의 상품정보 얻어오기
	ProductVO getProduct(ProductVO vo);

	// 상품 종류별 상품 목록 얻어오기
	List<ProductVO> getProductListByKind(String kind);

}