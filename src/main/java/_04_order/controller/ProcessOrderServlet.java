package _04_order.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _01_register.model.MemberBean;
import _04_order.model.OrderBean;
import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _04_order.service.OrderService;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;
import _05_product.service.ProductService;

/* 不考慮兩個商品庫存都不夠>< */

// 儲存會員的訂單
@WebServlet("/order/orderCheck")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 使用逾時，回首頁
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg); // 顯示錯誤訊息

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String buyCartStr = request.getParameter("buyCartStr");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

		// 篩選cart，如果是按直接購買
		if (buyCartStr.equals("true")) {
			cart = (ShoppingCart) session.getAttribute("buyCart");
		}

		// 如果購物車是空的 跳轉回首頁 但到時候可能在刪除時直接跳轉首頁
		if (cart == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		// 從瀏覽器取得訂購資料
		Integer memberId = mb.getId();
		String memberName = request.getParameter("name"); // 訂購人姓名 跟資料庫的不一樣
		Integer totalPrice = cart.getFinalSubtotal();
		String city = request.getParameter("county"); // 訂購人地址
		String area = request.getParameter("district"); // 訂購人地址
		String address = request.getParameter("address"); // 訂購人地址
		String phone = request.getParameter("phone"); // 訂購人電話
		String note = request.getParameter("note"); // 訂單備註
		Date today = new Date();
		// 封裝進OrderBean
		OrderBean ob = new OrderBean(null, memberId, memberName, totalPrice, city + area + address, phone, note, today,
				null, null, "待出貨", null);

		Map<Integer, Map<OrderItemBean, Set<ProductFormatBean>>> content = cart.getContent();
		Map<Integer, String> finalContent = cart.getCheckedMap();
		Set<Integer> set = content.keySet();
		Set<Integer> checkedSet = cart.getCheckedMap().keySet();

		// 篩選有被勾選的OrderItemBean裝入set內
		Set<OrderItemBean> items = new LinkedHashSet<>();
		for (Integer i : set) {
			for (Integer j : checkedSet) {
				if (i.equals(j) && finalContent.get(j).equals("y")) {
					Map<OrderItemBean, Set<ProductFormatBean>> orderMap = content.get(i);
					OrderItemBean oib = orderMap.keySet().iterator().next();
					// 檢查庫存
					WebApplicationContext ctx = WebApplicationContextUtils
							.getWebApplicationContext(getServletContext());
					ProductService productService = ctx.getBean(ProductService.class);
					ProductBean pb = productService.getProduct(oib.getProductId());
					Set<ProductFormatBean> formats = pb.getProductFormat();
					for (ProductFormatBean pfb : formats) {
						if (pfb.getFormatContent1().equals(oib.getFormatContent1())
								&& pfb.getFormatContent2().equals(oib.getFormatContent2())) {
							if (pfb.getStock() - oib.getQuantity() < 0) {
								errorMsg.put("stock", pb.getProductName() + "的" + pfb.getFormatContent1() + " "
										+ pfb.getFormatContent2() + " 庫存量不足!<br>庫存：" + pfb.getStock());
								RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
								rd.forward(request, response);
								return;
							}
						}
					}
					oib.setOrderBean(ob);
					items.add(oib);
				}
			}

		}
		// 裝入OrderBean
		ob.setOrderItems(items);

		try {
			// 儲存OrderBean
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			OrderService orderService = ctx.getBean(OrderService.class);
			orderService.persistOrder(ob, cart);

			session.setAttribute("orderNo", ob.getOrderNo());

			// 刪除cart，如果是按直接購買不刪除購物車
			if (buyCartStr.equals("true")) {
				orderService.persistOrder(ob);
				session.removeAttribute("buyCart");
			} else {
				orderService.persistOrder(ob, cart);
			}

			response.sendRedirect(response.encodeRedirectURL("../_04_order/orderSuccess.jsp"));
//			RequestDispatcher rd = request.getRequestDispatcher("/order/creditCard");
//			rd.forward(request, response);
			return;
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			String message = ex.getMessage();
			String shortMessage = "";
			shortMessage = message.substring(message.indexOf(":") + 1);
			session.setAttribute("OrderErrorMsg", "訂單發生異常" + shortMessage + "請更正訂單內容");

			response.sendRedirect(response.encodeRedirectURL(""));
			return;
		}
	}

}