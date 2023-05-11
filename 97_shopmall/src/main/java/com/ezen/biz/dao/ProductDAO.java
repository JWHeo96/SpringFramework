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
	
	// �Ż�ǰ ��� ������
	public List<ProductVO> getNewProductList(){
		return mybatis.selectList("ProductMapper.getNewProductList");
	}
	
	// ����Ʈ ��ǰ ������
	public List<ProductVO> getBestProductList(){
		return mybatis.selectList("ProductMapper.getBestProductList");
	} 
	
	// ��ǰ��ȣ�� �ϳ��� ��ǰ���� ������
	public ProductVO getProduct(ProductVO vo) {
		return mybatis.selectOne("ProductMapper.getProduct", vo);
	}
	
	// ��ǰ ������ ��ǰ ��� ������
	public List<ProductVO> getProductListByKind(String kind){
		return mybatis.selectList("ProductMapper.getProductListByKind", kind);
	}
}
