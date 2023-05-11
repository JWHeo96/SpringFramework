package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.ProductDAO;
import com.ezen.biz.dto.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public List<ProductVO> getNewProductList() {
		return productDAO.getNewProductList();
	}

	@Override
	public List<ProductVO> getBestProductList() {
		return productDAO.getBestProductList();
	}

	@Override
	public ProductVO getProduct(ProductVO vo) {
		return productDAO.getProduct(vo);
	}

	@Override
	public List<ProductVO> getProductListByKind(String kind) {
		return productDAO.getProductListByKind(kind);
	}

}
