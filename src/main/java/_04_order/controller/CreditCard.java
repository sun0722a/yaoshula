package _04_order.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutDevide;

// 準備跳轉至金流畫面
@WebServlet("/order/creditCard")
public class CreditCard extends HttpServlet {
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

		String orderNo = session.getAttribute("orderNo").toString();
		int number = (int) (Math.random() * 1000);

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrderService service = ctx.getBean(OrderService.class);
		OrderBean ob = null;
		ob = service.getOrder(Integer.parseInt(orderNo));

		// 設定金流
		AllInOne aio = new AllInOne("");
		AioCheckOutDevide aioCheck = new AioCheckOutDevide();
		aioCheck.setMerchantTradeNo(orderNo + number);
		aioCheck.setMerchantTradeDate(
				ob.getOrderDate().toString().replace("-", "/").substring(0, ob.getOrderDate().toString().length() - 2));
		aioCheck.setTotalAmount(ob.getTotalPrice().toString());
		aioCheck.setItemName("哈哈20元x1#西西20元x2");

		aioCheck.setMerchantTradeNo("testCompany0007");
		aioCheck.setMerchantTradeDate("2017/01/01 08:05:23");
		aioCheck.setTotalAmount("50");
		aioCheck.setTradeDesc("test Description");
		aioCheck.setItemName("TestItem");
		aioCheck.setReturnURL("http://211.23.128.214:5000");
		aioCheck.setNeedExtraPaidInfo("N");
		aioCheck.setCreditInstallment("3,6");
		aioCheck.setInstallmentAmount("10");
		// 輸出畫面
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(aio.aioCheckOut(aioCheck, null));

		return;
	}

}
