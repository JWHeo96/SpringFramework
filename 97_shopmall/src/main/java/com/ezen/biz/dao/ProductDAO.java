package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.ProductVO;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 신상품 목록 얻어오기
	public List<ProductVO> getNewProductList(){
		return mybatis.selectList("ProductMapper.getNewProductList");
	}
	
	// 베스트 상품 얻어오기
	public List<ProductVO> getBestProductList(){
		return mybatis.selectList("ProductMapper.getBestProductList");
	} 
	
	// 상품번호로 하나의 상품정보 얻어오기
	public ProductVO getProduct(ProductVO vo) {
		return mybatis.selectOne("ProductMapper.getProduct", vo);
	}
	
	// 상품 종류별 상품 목록 얻어오기
	public List<ProductVO> getProductListByKind(String kind){
		return mybatis.selectList("ProductMapper.getProductListByKind", kind);
	}
}
