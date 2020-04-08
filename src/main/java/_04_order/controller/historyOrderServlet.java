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

@WebServlet("/order/showHistoryOrder")
public class historyOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先取出session物件
		HttpSession session = request.getSession(false);

		// 使用逾時，回首頁
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		Integer memberId = mb.getId();

//		OrderService service = new OrderServiceImpl();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrderService service = ctx.getBean(OrderService.class);
		List<OrderBean> orders = service.getMemberOrders(memberId);

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
