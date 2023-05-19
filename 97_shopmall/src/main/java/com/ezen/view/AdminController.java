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
	 * ��ǰ ����Ʈ ���
	 */
	/* ����¡ ó�� �� �ҽ�
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
	*/
	
	@RequestMapping("/admin_product_list")
	public String adminProductList(@RequestParam(value="key", defaultValue="") String name,
			Criteria criteria, Model model) {
		// (1) �������� ��ǰ ��� ��ȸ
		List<ProductVO> productList = productService.getListProductWithPaging(criteria, name);
		
		// (2) ȭ�鿡 ǥ���� ������ ��ư ���� ����
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(productService.countProductList(name));	// �� �Խñ��� �� ����
		
		// (2) ���� ��ü�� ��ǰ ��� ����
		model.addAttribute("productList", productList);
		model.addAttribute("productListSize", productList.size());
		model.addAttribute("pageMaker", pageMaker);
		
		// (3) ȭ�� ȣ��: productList.jsp
		return "admin/product/productList";
	}
	
	/*
	 * ��ǰ �� ����
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
	 * ��ǰ ��� ȭ������ �̵�
	 */
	@GetMapping("/admin_product_write_form")
	public String adminProductWriteView(Model model) {
		
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	/*
	 * ��ǰ ��� ó��
	 */
	@PostMapping("/admin_product_write")
	public String adminProductWriteAction(ProductVO vo, HttpSession session,
			@RequestParam(value="product_image", defaultValue = "") MultipartFile uploadFile) {
		
		// ���ε� ������ ���ε� �Ǿ����� Ȯ��
		if (!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			
			// �̹����� ������ ��ġ ����
			// getServletContext() - ������Ʈ ���� ���� ����
			// getRealPath() - ������ ����� ���� ��ũ ���� ��ġ�� �˷��ִ� �޼ҵ�
			String image_path = session.getServletContext().
					getRealPath("WEB-INF/resources/product_images/");
			System.out.println(image_path);
			
			try {
				uploadFile.transferTo(new File(image_path + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} 
		
		// �Է��� ��ǰ���� ���� 
		productService.insertProduct(vo);
		
		return "redirect:admin_product_list";
	}

	/*
	 * ��ǰ ���� ȭ������ �̵�
	 */
	@GetMapping("admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		
		// (1) ��ǰ �� ��ȸ
		
		// (2) Model ��ü�� ��ǰ ������ ����
				
		// (3) ȭ��  ǥ��
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product);
		
		String kindList[] = {"Heels", "Boots", "Sandals", "Slipers", "Sneekers", "Sale"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productUpdate";
	}
	
	/*
	 * ��ǰ ���� ó��
	 */
	@PostMapping("admin_product_update")
	public String adminProductUpdate(ProductVO vo, HttpSession session,
			@RequestParam(value="nonmakeImg") String org_image,
			@RequestParam(value="product_image") MultipartFile uploadFile) {
		
		
		// ���ε� ������ ���ε� �Ǿ����� Ȯ��
		if (!uploadFile.isEmpty()) {	// ��ǰ�̹����� ����� ���
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			
			// �̹����� ������ ��ġ ����
			String image_path = session.getServletContext().
					getRealPath("WEB-INF/resources/product_images/");
			
			// getServletContext() - ������Ʈ ���� ���� ����
			// getRealPath() - ������ ����� ���� ��ũ ���� ��ġ�� �˷��ִ� �޼ҵ�
			
			try {
				uploadFile.transferTo(new File(image_path + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {	// ��ǰ�̹����� ������� ���� ���
			vo.setImage(org_image);
		}
		
		// ����Ʈ ��ǰ, �ű� ��ǰ 

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
		// �Է��� ��ǰ���� ���� 
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
