package _04_order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _05_product.model.ProductBean;
import _05_product.service.ProductService;
import _05_product.service.impl.ProductServiceImpl;


@WebServlet("/order/checkOrder")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		
		//透過productInfoServlet取得product的session
		String productIdStr = session.getAttribute("productId").toString();
		System.out.println(productIdStr);
		Integer productId = Integer.parseInt(productIdStr.trim());
		
		
		ProductService service = new ProductServiceImpl();
		ProductBean pb = service.getProduct(productId);
		
		
		//檢查有沒有取得選取規格的值
		String content1 = request.getParameter("content1");
		String content2 = request.getParameter("content2");
		String qtytr = request.getParameter("qty");
		
		
		//如果沒有的話要再回去商品詳細的頁面
		if (qtytr == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/product/ShowProductInfo?productId=" + productId);
			rd.forward(request, response);
			return;
		} else {
			request.setAttribute("content1", content1);
			request.setAttribute("content2", content2);
			request.setAttribute("qty", qtytr);
			request.setAttribute("productInfo", pb);
			
			System.out.println(content1);
			System.out.println(content2);
			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/checkOrder.jsp");
			rd.forward(request, response);
			return;
		}
		
	}
	

}
