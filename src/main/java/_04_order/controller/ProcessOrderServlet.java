package _04_order.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _01_register.service.MemberService;
import _01_register.service.impl.MemberServiceImpl;
import _04_order.model.OrderBean;
import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _04_order.service.OrderService;
import _04_order.service.impl.OrderServiceImpl;
import _05_product.model.ProductFormatBean;

<<<<<<< HEAD
//儲存會員的訂單
@WebServlet("/order/orderProcess")
=======
// 儲存會員的訂單
@WebServlet("/order/orderCheck")
>>>>>>> 7a4065b70d0f7738c36cb5c4c6c705564032ba5f
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
<<<<<<< HEAD
		
=======

		String finalDecision = request.getParameter("finalDecision");
>>>>>>> 7a4065b70d0f7738c36cb5c4c6c705564032ba5f
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
<<<<<<< HEAD
//		//如果購物車是空的　跳轉回首頁　但到時候可能在刪除時直接跳轉首頁
//		if(cart == null) {
//			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
//			return;
//		}
//		if(finalDecision.equals("Cancel")) {
//			session.removeAttribute("ShoppingCart");
//			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
//			return;
//		}
		
		Integer memberId = mb.getId();
		String memberIdStr = memberId.toString();
		String memberName = request.getParameter("name");	//訂購人姓名 跟資料庫的不一樣
		Integer totalPrice = cart.getSubTotal();		//總金額
//		String totalStr  = request.getParameter("")
		String address = request.getParameter("address");	//訂購人地址
		String phone = request.getParameter("phoneNumber"); //訂購人電話
		String note = request.getParameter("note"); //訂單備註
		Date today = new Date();
		
		OrderBean ob = new OrderBean(null,memberIdStr,memberName,totalPrice,address,phone,note,today,null,null,"待出貨",null);
		Map<Integer, OrderItemBean> content = cart.getContent();
		
=======
		// 如果購物車是空的 跳轉回首頁 但到時候可能在刪除時直接跳轉首頁
		if (cart == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		if (finalDecision.equals("Cancel")) {
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
			return;
		}
		String memberId = mb.getName();
		String memberName = request.getParameter("name"); // 訂購人姓名 跟資料庫的不一樣
		Integer totalPrice = cart.getSubTotal(); // 總金額
		String address = request.getParameter("address"); // 訂購人地址
		String phone = request.getParameter("phoneNumber"); // 訂購人電話
		String note = request.getParameter("note"); // 訂單備註
		Date today = new Date();

		OrderBean ob = new OrderBean(null, memberId, memberName, totalPrice, address, phone, note, today, null, null,
				"待出貨", null);
		Map<Integer, Map<OrderItemBean, Set<ProductFormatBean>>> content = cart.getContent();

>>>>>>> 7a4065b70d0f7738c36cb5c4c6c705564032ba5f
		Set<OrderItemBean> items = new LinkedHashSet<>();
		Set<Integer> set = content.keySet();
		for (Integer i : set) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = content.get(i);
			OrderItemBean oib = orderMap.keySet().iterator().next();
			oib.setOrderBean(ob);
			items.add(oib);
		}

		ob.setOrderItems(items);
		try {
			OrderService orderService = new OrderServiceImpl();
			orderService.persistOrder(ob);
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL(""));
			return;
		} catch (RuntimeException ex) {
			String message = ex.getMessage();
			String shortMessage = "";
			shortMessage = message.substring(message.indexOf(":") + 1);
			session.setAttribute("OrderErrorMsg", "訂單發生異常" + shortMessage + "請更正訂單內容");
			response.sendRedirect(response.encodeRedirectURL(""));
		}
	}

}
