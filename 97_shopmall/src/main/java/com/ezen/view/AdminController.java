package com.ezen.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.service.AdminService;
import com.ezen.biz.service.OrderService;
import com.ezen.biz.service.ProductService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	
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
	 * 상품 리스트 출력
	 */
	/* 페이징 처리 전 소스
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
	*/
	
	@RequestMapping("/admin_product_list")
	public String adminProductList(@RequestParam(value="key", defaultValue="") String name,
			Criteria criteria, Model model) {
		// (1) 페이지별 상품 목록 조회
		List<ProductVO> productList = productService.getListProductWithPaging(criteria, name);
		
		// (2) 화면에 표시할 페이지 버튼 정보 설정
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(productService.countProductList(name));	// 총 게시글의 수 저장
		
		// (2) 내장 객체에 상품 목록 저장
		model.addAttribute("productList", productList);
		model.addAttribute("productListSize", productList.size());
		model.addAttribute("pageMaker", pageMaker);
		
		// (3) 화면 호출: productList.jsp
		return "admin/product/productList";
	}
	
	/*
	 * 상품 상세 보기
	 */
	@RequestMapping("/admin_product_detail")
	public String adminProductDetail(Criteria cri, ProductVO vo, Model model) {
		
		ProductVO product = productService.getProduct(vo);
		
		String[] kindList = {"0", "Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		int index = Integer.parseInt(product.getKind());
		
		model.addAttribute("productVO", product);
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("cri", cri);
		
		return "admin/product/productDetail";
	}
	
	/*
	 * 상품 등록 화면으로 이동
	 */
	@GetMapping("/admin_product_write_form")
	public String adminProductWriteView(Model model) {
		
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	/*
	 * 상품 등록 처리
	 */
	@PostMapping("/admin_product_write")
	public String adminProductWriteAction(ProductVO vo, HttpSession session,
			@RequestParam(value="product_image", defaultValue = "") MultipartFile uploadFile) {
		
		// 업로드 파일이 업로드 되었는지 확인
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			
			// 이미지를 저장할 위치 지정
			// getServletContext() - 프로젝트 관련 정보 리턴
			// getRealPath() - 지정된 경로의 실제 디스크 상의 위치를 알려주는 메소드
			String image_path = session.getServletContext().
					getRealPath("WEB-INF/resources/product_images/");
			System.out.println(image_path);
			
			try {
				uploadFile.transferTo(new File(image_path + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} 
		
		// 입력한 상품정보 저장 
		productService.insertProduct(vo);
		
		return "redirect:admin_product_list";
	}

	/*
	 * 상품 수정 화면으로 이동
	 */
	@GetMapping("admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		
		// (1) 상품 상세 조회
		
		// (2) Model 객체에 상품 데이터 저장
				
		// (3) 화면  표출
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productUpdate";
	}
	
	/*
	 * 상품 수정 처리
	 */
	@PostMapping("admin_product_update")
	public String adminProductUpdate(ProductVO vo, HttpSession session,
			@RequestParam(value="nonmakeImg") String org_image,
			@RequestParam(value="product_image") MultipartFile uploadFile) {
		
		
		// 업로드 파일이 업로드 되었는지 확인
		if (!uploadFile.isEmpty()) {	// 상품이미지가 변경된 경우
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			
			// 이미지를 저장할 위치 지정
			String image_path = session.getServletContext().
					getRealPath("WEB-INF/resources/product_images/");
			
			// getServletContext() - 프로젝트 관련 정보 리턴
			// getRealPath() - 지정된 경로의 실제 디스크 상의 위치를 알려주는 메소드
			
			try {
				uploadFile.transferTo(new File(image_path + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {	// 상품이미지가 변경되지 않은 경우
			vo.setImage(org_image);
		}
		
		// 베스트 상품, 신규 상품 

		if (vo.getUseyn() == null || vo.getUseyn().equals("n") ) {
			vo.setUseyn("n");
		} else {
			vo.setUseyn("y");
		}
		
		if (vo.getBestyn() == null || vo.getBestyn().equals("n") ) {
			vo.setBestyn("n");
		} else {
			vo.setBestyn("y");
		}
		System.out.println(vo);
		// 입력한 상품정보 저장 
		productService.updateProduct(vo);
		
		return "redirect:admin_product_list";
	}
	
	@GetMapping("/admin_order_list")
	public String adminOrderList(Model model,
			@RequestParam(value="key", defaultValue = "") String key) {
		
		List<OrderVO> orderList = orderService.getListOrder(key);
		
		model.addAttribute("orderList", orderList);
		
		return "admin/order/orderList";
	}
	
	@PostMapping("admin_order_save")
	public String adminOrderSave(OrderVO vo,
			@RequestParam(value="result") int oseq[]) {
		
		for (int order : oseq) {
			orderService.updateOrderResult(order);
		}
		
		
		
		return "redirect:admin_order_list";
	}
}
