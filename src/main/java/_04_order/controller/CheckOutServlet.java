package _04_order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _05_product.model.ProductBean;
import _05_product.service.ProductService;
import _05_product.service.impl.ProductServiceImpl;


@WebServlet("/order/checkOrder")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		
		
//			String requestURI = request.getRequestURI();
			
			if(session == null) {
//				response.sendRedirect(response.encodeRedirectURL(request.getContextPath())+ "/_02_login/login.jsp");
				getServletContext().getRequestDispatcher("/_02_login/login.jsp").forward(request, response);
//				return;
			}
			
				
//			Integer memberId = mb.getId();
//			Integer productId = pb.getProductId();
//			System.out.println(productId);
//			Integer productId = pb.getProductId();
//			System.out.println(productId);
//			String productName = request.getParameter("productName");
			
			String content1 = request.getParameter("content1");
			String content2 = request.getParameter("content2");
//			String price = request.getParameter("price");
			String qtyStr = request.getParameter("qty");
//			Integer qty = Integer.parseInt(qtyStr);
			
//			ProductService service = new ProductServiceImpl();
			
			
//			String productName = pb.getProductName();
//			Integer price = pb.getPrice();
//			Integer subtotal = qty * price;
			
//			System.out.println("名稱: " + productName);
			System.out.println("規格1:" + content1);
			System.out.println("規格2" + content2);
//			System.out.println("價錢: " + price);
//			System.out.println("數量"  + qty);
//			System.out.println("商品ID" + productId);
//			System.out.println("總金額" + subtotal);
			
//			session.setAttribute("LoginOK", mb);
			
		
			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/checkOrder.jsp");
			rd.forward(request, response);
			return;
		}
		
	}


