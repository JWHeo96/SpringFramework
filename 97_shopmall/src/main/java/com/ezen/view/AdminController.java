package com.ezen.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.service.AdminService;
import com.ezen.biz.service.ProductService;

@Controller
@SessionAttributes("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ProductService productService;
	
	/*
	 * �α��� ȭ�� �̵�
	 */
	@GetMapping("/admin_login_form")
	public String adminLoginView() {
		
		return "admin/main";
	}
	
	/*
	 * �α��� ��� ó��
	 */
	@PostMapping("/admin_login")
	public String adminLoginAction(AdminVO vo, Model model) {
		/// (1) ������ ID ����
		int result = adminService.adminCheck(vo);
		
		// (2) ��������� �̸�
		//		-- ������ ���� ��ȸ
		//		-- ��ǰ��� ȭ������ �̵�
		if (result == 1) {
			model.addAttribute("admin",adminService.getAdmin(vo.getId()));
			
			return "redirect:admin_product_list";
		} else {
			
			// (3) ������ �������̸�
			//		-- �޽����� �����ϰ� �α���ȭ������ �̵�
			if (result == 0 || result == -1) {
				model.addAttribute("message", "���̵�, ��й�ȣ�� Ȯ���� �ּ���.");
			}
		}
			return "admin/main";
	}
	
	/*
	 * �α׾ƿ� ��� ó��
	 */
	@GetMapping("/admin_logout")
	public String adminLogout(SessionStatus status) {
		
		status.setComplete();
		
		return "admin/main";
	}
	
	/*
	 * Post ��� ��ǰ ����Ʈ ���
	 */
	/*
	 * @PostMapping("/admin_product_list") public String adminProductList2(Model
	 * model) {
	 * 
	 * // (3) ȭ�� ȣ��: productList.jsp" return "redirect:admin_product_list"; }
	 */
	
	/*
	 * Get ��� ��ǰ ����Ʈ ���
	 */
	@RequestMapping("/admin_product_list")
	public String adminProductList(@RequestParam(value="key", defaultValue = "") String name, 
			Model model) {
		// (1) ��ü ��ǰ��� ��ȸ
		List<ProductVO> productList = productService.getListProduct(name);
		
		// (2) ���� ��ü�� ��ǰ ��� ����
		model.addAttribute("productList", productList);
		model.addAttribute("productListSize", productList.size());
		
		// (3) ȭ�� ȣ��: productList.jsp"
		return "admin/product/productList";
	}
	
	/*
	 * ��ǰ �� ����
	 */
	@RequestMapping("/admin_product_detail")
	public String adminProductDetail(ProductVO vo, Model model) {
		
		ProductVO product = productService.getProduct(vo);
		
		String[] kindList = {"0", "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		int index = Integer.parseInt(product.getKind());
		
		model.addAttribute("productVO", product);
		model.addAttribute("kind", kindList[index]);
		
		return "admin/product/productDetail";
	}
	
	/*
	 * ��ǰ ���� ȭ������ �̵�
	 */
	@PostMapping("admin_product_update_form")
	public String updateProductView() {
		return "admin/product/productUpdate";
	}
}
