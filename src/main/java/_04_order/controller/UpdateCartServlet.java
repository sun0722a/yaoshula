package _04_order.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_order.model.ShoppingCart;

/* 返回首頁: 商城首頁? */

// 修改購物車內的商品資料，(刪除商品、修改數量、修改規格、修改單選、修改全選)
@WebServlet("/order/updateShoppingCart")
public class UpdateCartServlet extends HttpServlet {
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

		// 如果找不到購物車(通常是Session逾時)，回到首頁
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (sc == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		// cmd可能是DEL或是QTY或是FMT或是CHS或是CSA
		String cmd = request.getParameter("cmd");
		String productFormatIdStr = request.getParameter("productFormatId");
		int productFormatId = 0;
		if (productFormatIdStr != null) {
			productFormatId = Integer.parseInt(productFormatIdStr);
		}

		if (cmd.equalsIgnoreCase("DEL")) {
			// 刪除購物車內的某項商品
			sc.deleteProduct(productFormatId);

			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
			return;
		} else if (cmd.equalsIgnoreCase("QTY")) {
			// 修改某項商品的數量
			String newQtyStr = request.getParameter("newQty");
			int newQty = Integer.parseInt(newQtyStr.trim());
			sc.changeQty(productFormatId, newQty);

			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
			return;
		} else if (cmd.equalsIgnoreCase("FMT")) {
			// 修改某項商品的規格
			String[] newFmt = request.getParameter("newFmt").split(",");
			String content1 = null;
			String content2 = null;
			if (newFmt.length == 2) {
				content1 = newFmt[0];
				content2 = newFmt[1];
			} else if (newFmt.length == 1) {
				content1 = newFmt[0];
			}
			sc.changeFormat(productFormatId, content1, content2);

			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
			return;
		} else if (cmd.equalsIgnoreCase("CHS")) {
			// 修改某項商品的選擇項
			String choose = request.getParameter("choose");
			sc.changeChecked(productFormatId, choose);

			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
		} else if (cmd.equalsIgnoreCase("CSA")) {
			// 修改全部商品的選擇項
			String chooseAll = request.getParameter("chooseAll");
			sc.changeAllChecked(chooseAll);

			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
		}
	}
}