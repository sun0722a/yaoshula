package _04_order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import _04_order.service.OrderService;

/* 用session傳?? */

// 查詢歷史訂單
@WebServlet("/order/showHistoryOrder")
public class historyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 使用逾時，回首頁
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		// 取得使用者資料(MemberBean)
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		Integer memberId = mb.getId();
		// 取得使用者的訂單資料(OrderBean)
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrderService service = ctx.getBean(OrderService.class);
		List<OrderBean> orders = service.getMemberOrders(memberId);

		// 取出訂單詳細資料(OrderItemBean)
		Map<Integer, Set<OrderItemBean>> orderItemGroup = new HashMap<Integer, Set<OrderItemBean>>();
		for (int i = 0; i < orders.size(); i++) {
			int orderNo = orders.get(i).getOrderNo();
			Set<OrderItemBean> OrderItemBeans = orders.get(i).getOrderItems();
			orderItemGroup.put(orderNo, OrderItemBeans);
		}

		session.setAttribute("order_list", orders);
		session.setAttribute("orderItem_map", orderItemGroup);

		RequestDispatcher rd = request.getRequestDispatcher("/_04_order/historyOrder.jsp");
		rd.forward(request, response);
		return;

	}

}
