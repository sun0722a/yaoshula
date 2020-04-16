package _08_manager.order;

import java.io.IOException;
import java.util.Date;

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
import _04_order.service.OrderService;

// 新增出貨日期or到貨日期
@WebServlet("/manager/addOrderDate")
public class AddOrderDateServlet extends HttpServlet {
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
		String cmd = request.getParameter("cmd");
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			;
		}

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrderService orderService = ctx.getBean(OrderService.class);
		OrderBean ob = orderService.getOrder(id);
		Date today = new Date();

		// 更新狀態
		if (cmd.equals("shippingDate")) {
			ob.setShippingDate(today);
			ob.setStatus("已出貨");
			orderService.insertOrder(ob);
		} else if (cmd.equals("arriveDate")) {
			ob.setArriveDate(today);
			ob.setStatus("完成");
			orderService.insertOrder(ob);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/manager/showOrders?searchStr=" + searchStr);
		rd.forward(request, response);
		return;
	}

}
