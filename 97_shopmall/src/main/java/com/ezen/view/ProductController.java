package com.ezen.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/product_detail")
	public String productDetail(ProductVO vo, Model model) {
		
		// ��ǰ �� ��ȸ
		ProductVO product = productService.getProduct(vo);

		// JSP�� ��� ����
		model.addAttribute("productVO", product);
		
		return "product/productDetail";
	}
	
	@RequestMapping(value="/category")
	public String productKindAction(ProductVO vo, Model model) {
		
		List<ProductVO> product = (ArrayList<ProductVO>) productService.getProductListByKind(vo.getKind());
		
		model.addAttribute("productKindList", product);
		
		return "product/productKind";
	}
}
