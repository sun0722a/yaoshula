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

// 本類別可修改購物車內的商品資料，包括刪除某項商品，修改某項商品的數量
@WebServlet("/order/updateShoppingCart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 使用逾時，回首頁
		session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		// 取出session物件內的ShoppingCart物件
		ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
		if (sc == null) {
			// 如果找不到購物車(通常是Session逾時)，沒有必要往下執行，導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		// cmd可能是DEL或是MOD
		String cmd = request.getParameter("cmd");
		String productFormatIdStr = request.getParameter("productFormatId");
		int productFormatId = Integer.parseInt(productFormatIdStr.trim());

		if (cmd.equalsIgnoreCase("DEL")) {
			// 刪除購物車內的某項商品
			sc.deleteProduct(productFormatId);
			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
			return;
		} else if (cmd.equalsIgnoreCase("QTY")) {
			String newQtyStr = request.getParameter("newQty");
			int newQty = Integer.parseInt(newQtyStr.trim());
			// 修改某項商品的數量
			sc.changeQty(productFormatId, newQty);
			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
			return;
		} else if (cmd.equalsIgnoreCase("FMT")) {
			String[] newFmt = request.getParameter("newFmt").split(",");
			String content1 = null;
			String content2 = null;
			if (newFmt.length == 2) {
				System.out.println("length=2");
				content1 = newFmt[0];
				content2 = newFmt[1];
			} else if (newFmt.length == 1) {
				content1 = newFmt[0];
			}

			// 修改某項商品的規格
			sc.changeFormat(productFormatId, content1, content2);
			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/shoppingCart.jsp");
			rd.forward(request, response);
			return;
		}
	}
}