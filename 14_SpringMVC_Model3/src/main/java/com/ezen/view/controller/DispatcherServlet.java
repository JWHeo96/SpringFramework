package com.ezen.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.biz.dao.BoardDAO;
import com.ezen.biz.dto.BoardVO;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	// DispatcherServlet ���� ȣ��� �ʱ�ȭ ����
    // HandlerMapping, ViewResolver ��ü �ʱ�ȭ
	@Override
	public void init() throws ServletException {
		super.init();
		
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST ������� ��û�� �����ʹ� ���ڵ� ó�� �ʿ�
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// (1) Client ������ ����
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		System.out.println("path=" + path);
		
		// (2) HandlerMapping���� path�� �ش��ϴ� Controller ��ȸ
		Controller controller = handlerMapping.getController(path);
		
		// (3) ������ ��ȸ�� Controller ����
		String viewName = controller.handleRequest(request, response);
		
		// (4) ViewResolver�� ���� view ����
		String view = "";
		if (viewName.contains(".do")) {
			view = viewName;
		} else {
			view = viewResolver.getView(viewName);
		}
		
		// (5) �˻��� ȭ������ �̵��Ѵ�.
		response.sendRedirect(view);
	}

}
