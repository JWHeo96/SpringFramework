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
	 * 로그인 화면 이동
	 */
	@GetMapping("/admin_login_form")
	public String adminLoginView() {
		
		return "admin/main";
	}
	
	/*
	 * 로그인 기능 처리
	 */
	@PostMapping("/admin_login")
	public String adminLoginAction(AdminVO vo, Model model) {
		/// (1) 관리자 ID 인증
		int result = adminService.adminCheck(vo);
		
		// (2) 정상관리자 이면
		//		-- 관리자 정보 조회
		//		-- 상품목록 화면으로 이동
		if (result == 1) {
			model.addAttribute("admin",adminService.getAdmin(vo.getId()));
			
			return "redirect:admin_product_list";
		} else {
			
			// (3) 비정상 관리자이면
			//		-- 메시지를 설정하고 로그인화면으로 이동
			if (result == 0 || result == -1) {
				model.addAttribute("message", "아이디, 비밀번호를 확인해 주세요.");
			}
		}
			return "admin/main";
	}
	
	/*
	 * 로그아웃 기능 처리
	 */
	@GetMapping("/admin_logout")
	public String adminLogout(SessionStatus status) {
		
		status.setComplete();
		
		return "admin/main";
	}
	
	/*
	 * Post 방식 상품 리스트 출력
	 */
	/*
	 * @PostMapping("/admin_product_list") public String adminProductList2(Model
	 * model) {
	 * 
	 * // (3) 화면 호출: productList.jsp" return "redirect:admin_product_list"; }
	 */
	
	/*
	 * Get 방식 상품 리스트 출력
	 */
	@RequestMapping("/admin_product_list")
	public String adminProductList(@RequestParam(value="key", defaultValue = "") String name, 
			Model model) {
		// (1) 전체 상품목록 조회
		List<ProductVO> productList = productService.getListProduct(name);
		
		// (2) 내장 객체에 상품 목록 저장
		model.addAttribute("productList", productList);
		model.addAttribute("productListSize", productList.size());
		
		// (3) 화면 호출: productList.jsp"
		return "admin/product/productList";
	}
	
	/*
	 * 상품 상세 보기
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
	 * 상품 수정 화면으로 이동
	 */
	@PostMapping("admin_product_update_form")
	public String updateProductView() {
		return "admin/product/productUpdate";
	}
}
