package _08_manager.order;

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

import _04_order.model.OrderBean;
import _04_order.model.OrderItemBean;
import _04_order.service.OrderService;

// 查詢訂單
@WebServlet("/manager/showOrders")
public class ShowOrdersServlet extends HttpServlet {
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

		// 必須是空字串
		String searchStr = request.getParameter("searchStr") == null ? "" : request.getParameter("searchStr");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrderService service = ctx.getBean(OrderService.class);

		List<OrderBean> orders = service.getAllOrders(searchStr);
		// 取出訂單詳細資料(OrderItemBean)
		Map<Integer, Set<OrderItemBean>> orderItemGroup = new HashMap<Integer, Set<OrderItemBean>>();
		for (OrderBean ob : orders) {
			int orderNo = ob.getOrderNo();
			Set<OrderItemBean> OrderItemBeans = ob.getOrderItems();
			orderItemGroup.put(orderNo, OrderItemBeans);
		}

		request.setAttribute("searchStr", searchStr);
		request.setAttribute("order_list", orders);
		request.setAttribute("orderItem_map", orderItemGroup);

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/order/allOrders.jsp");
		rd.forward(request, response);
		return;
	}

}
