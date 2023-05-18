package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

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
	
	/*
	 * ��ü ��ǰ��Ͽ� ���� ��ǰ ���� ��ȸ
	 * �Է� �Ķ����:
	 * 		name : �˻��� ��ǰ�� (���� ""�� ��� ��ü ��ǰ���� ��ȸ) 
	 */
	public int countProductList(String name) {
		return mybatis.selectOne("ProductMapper.countProductList", name);
	}
	
	// ��ǰ ��� ��ȸ
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("ProductMapper.listProduct", name);
	}

	/*
	 * �������� ��ǰ ��� ��ȸ
	 * �Է� �Ķ����:
	 * 		Criteria - ���� ������ ����
	 * 		name - ��ǰ�� �˻���
	 */
	public List<ProductVO> listProductWithPaging(Criteria criteria, String name) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("name", name);
		
		return mybatis.selectList("ProductMapper.listProductWithPaging", map);
	}
	
	// ��ǰ �߰�
	public void insertProduct(ProductVO vo) {
		mybatis.insert("ProductMapper.insertProduct", vo);
	}
	
	// ��ǰ ����
	public void updateProduct(ProductVO vo) {
		mybatis.update("ProductMapper.updateProduct", vo);
	}
}
