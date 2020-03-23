package _04_order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CheckUserLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			response.sendRedirect(response.encodeRedirectURL(
					request.getContextPath() + "/_02_login/login.jsp"));
		
		}
//			Map<String,String> message = new HashMap<>();
//		request.setAttribute("noStockMsg", message);
//		
//		String productName = request.getParameter("productName");
//		String price = request.getParameter("price");
//		String categary = request.getParameter("categary");
//		String detail = request.getParameter("detail");
//		String stockstr = request.getParameter("stock");
//		Integer stock = Integer.parseInt(stockstr);
//		
//		if(stock <= 0) {
//			message.put("noStock", "目前沒有庫存喔!");
//			
//			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/productInfo.jsp");
//			rd.forward(request, response);
//			return;
		}
	

	
	
}
