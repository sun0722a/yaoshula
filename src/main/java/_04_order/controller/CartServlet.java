package _04_order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;


@WebServlet("/order/ShoppingCart")
public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//只要舊的session物件 如果沒有就傳null讓使用者去登入
		HttpSession session = request.getSession(false);
		
		//紀錄之前使用者的網頁位置 登入成功後可以回到原本的地方
		String requestURI = request.getRequestURI();
		
		if(session == null || session.isNew()){
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/_02_login/login.jsp"));
			return;
		}
		
		session.setAttribute("requestURI", requestURI);
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if(mb == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_02_login/login.jsp"));
		}
		//取出session物件裡的購物車資料
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		
		//如果session內沒有購物車物件 就新建一個session物件
		if(cart == null) {
			cart = new ShoppingCart();
			
			session.setAttribute("ShoppingCart", cart);
		}
		
		String productIdStr = request.getParameter("productId");
		Integer productId = Integer.parseInt(productIdStr.trim());
		
		String qtyStr = request.getParameter("qty");
		Integer qty = 0;
		Map<Integer, ProductBean> productMap = (Map<Integer, ProductBean>) session.getAttribute("products_map");
		ProductBean bean = productMap.get(productId);
		
//		try {
//			qty = Integer.parseInt(qtyStr.trim());
//		}catch(NumberFormatException e) {
//			throw new ServletException(e);
//		}

		OrderItemBean oib = new OrderItemBean(null,productId,bean.getProductName(),null,null,bean.getPrice(),qty,null);
		cart.addToCart(productId, oib);
		RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
		rd.forward(request, response);
		return;
	}
	
}
