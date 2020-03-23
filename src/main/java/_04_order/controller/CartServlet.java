package _04_order.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _05_product.model.ProductBean;


@WebServlet("/ShoppingCart.do")
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
		
		if(session == null){
			response.sendRedirect(response.encodeRedirectURL(
					request.getContextPath()+ "/_02_login/login.jsp"));
			return;
		}
		//取出session物件裡的購物車資料
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		
		//如果session內沒有購物車物件 就新建一個session物件
		if(cart == null) {
			cart = new ShoppingCart();
			
			session.setAttribute("ShoppingCart", cart);
		}
		
		String productIdStr = request.getParameter("productId");
		Integer productId = Integer.parseInt(productIdStr);
		
		String qtyStr = request.getParameter("qty");
		Integer qty = Integer.parseInt(qtyStr);
		Map<Integer, ProductBean> productMap = (Map<Integer, ProductBean>) session.getAttribute("products_map");
		ProductBean bean = productMap.get(productId);
		
		
		OrderItemBean oib = new OrderItemBean(null,null,bean.getProductName(),bean.getPrice(),qty);
		cart.addToCart(productId, oib);
		RequestDispatcher rd = request.getRequestDispatcher("");
		rd.forward(request, response);
	}
	
}
