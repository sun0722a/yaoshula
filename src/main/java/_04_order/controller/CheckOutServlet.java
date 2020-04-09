package _04_order.controller;

import java.io.IOException;
import java.util.HashMap;
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

import _04_order.model.OrderItemBean;
import _04_order.model.ShoppingCart;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;
import _05_product.service.ProductService;

/* new shoppingCart會被洗掉 */
/* 未完成: 未將errormsg放入JSP(productinfo)  */

@WebServlet("/order/checkOrder")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg); // 顯示錯誤訊息

//		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

		// 如果session內沒有購物車物件 就新建一個session物件
//		if (cart == null) {
//			cart = new ShoppingCart();
//			session.setAttribute("ShoppingCart", cart);
//		}
		ShoppingCart cart = new ShoppingCart();
		// EL會依序從Page、Request、Session、Application範圍查詢
		request.setAttribute("ShoppingCart", cart);

		// 透過productInfoServlet取得product的session
		String productIdStr = session.getAttribute("productId").toString();
//		System.out.println(productIdStr);
		Integer productId = Integer.parseInt(productIdStr.trim());

//		ProductService service = new ProductServiceImpl();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ProductService service = ctx.getBean(ProductService.class);
		ProductBean pb = service.getProduct(productId);

		// 檢查有沒有取得選取規格的值
		String content1 = request.getParameter("content1") == null ? "" : request.getParameter("content1");
		String content2 = request.getParameter("content2") == null ? "" : request.getParameter("content2");
		String qtytr = request.getParameter("qty");
//		Integer price = pb.getPrice();

//		System.out.println(price);
//		System.out.println(content1);
//		System.out.println(content2);
		// 如果沒有的話要再回去商品詳細的頁面
		if (qtytr == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/product/ShowProductInfo?productId=" + productId);
			rd.forward(request, response);
			return;
		} else {
			Integer qty = Integer.parseInt(qtytr.trim());

			Set<ProductFormatBean> formats = pb.getProductFormat();
			Integer productFormatId = 0;
			for (ProductFormatBean pfb : formats) {
				if (pfb.getFormatContent1().equals(content1) && pfb.getFormatContent2().equals(content2)) {
					// 正確規格，則把productFormatId存下來
					productFormatId = pfb.getProductFormatId();
					// 檢查庫存
					if (pfb.getStock() - qty < 0) {
						errorMsg.put("stock", pfb.getFormatContent1() + "  " + pfb.getFormatContent2()
								+ " 庫存量不足!<br>庫存：" + pfb.getStock());
						RequestDispatcher rd = request
								.getRequestDispatcher("/product/ShowProductInfo?productId=" + productId);
						rd.forward(request, response);
						return;
					}
				}
			}

			OrderItemBean oib = new OrderItemBean(null, productId, pb.getProductName(), content1, content2,
					pb.getPrice(), qty, null);
			cart.addToCart(productFormatId, oib, formats);

			RequestDispatcher rd = request.getRequestDispatcher("/_04_order/checkOrder.jsp");
			rd.forward(request, response);
			return;
		}

	}

}
