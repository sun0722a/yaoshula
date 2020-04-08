package _04_order.controller;

import java.io.IOException;
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

// 把商品裝入購物車的部分
@WebServlet("/order/shoppingCart")
public class CartServlet extends HttpServlet {

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

		// 取出session物件裡的購物車資料
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");

		// 如果session內沒有購物車物件 就新建一個session物件
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("ShoppingCart", cart);
		}

		// 取得瀏覽器傳來的資料
		String productIdStr = session.getAttribute("productId").toString();
		Integer productId = Integer.parseInt(productIdStr.trim());

		// 如果無規格讓content的值為空字串，以便與資料庫進行比對
		String content1 = request.getParameter("content1") == null ? "" : request.getParameter("content1");
		String content2 = request.getParameter("content2") == null ? "" : request.getParameter("content2");
		String qtyStr = request.getParameter("qty");
		if (qtyStr == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/product/ShowProductInfo?productId=" + productId);
			rd.forward(request, response);
			return;
		}
		Integer qty = Integer.parseInt(qtyStr.trim());

//		Map<Integer, ProductBean> productMap = (Map<Integer, ProductBean>) session.getAttribute("products_map");
//		ProductBean bean = productMap.get(productId);
		// 透過 service & productId 取得商品資訊
//		ProductService productService = new ProductServiceImpl();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ProductService productService = ctx.getBean(ProductService.class);
		ProductBean pb = productService.getProduct(productId);
		// 取得商品的productFormat，進行比對
		Set<ProductFormatBean> formats = pb.getProductFormat();
		Integer productFormatId = 0;
		for (ProductFormatBean pfb : formats) {
			if (pfb.getFormatContent1().equals(content1) && pfb.getFormatContent2().equals(content2)) {
				// 正確規格，則把productFormatId存下來
				productFormatId = pfb.getProductFormatId();

			}
		}

		// 如果找不到規格相同的商品，就不做事
		if (productFormatId == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/product/ShowProductInfo?productId=" + productId);
			rd.forward(request, response);
			return;
		}
		// 把資料封裝進OrderItemBean
		OrderItemBean oib = new OrderItemBean(null, productId, pb.getProductName(), content1, content2, pb.getPrice(),
				qty, null);

		// 為了之後能抓選取的勾勾(預設為勾起來)[y, n]
		cart.addToCart(productFormatId, oib, formats);
//		System.out.println("checkedMapKey:" + cart.getCheckedMap().keySet());
//		System.out.println("checkedMapValues:" + cart.getCheckedMap().values());
//		System.out.println("cartKey" + cart.getContent().keySet());
		RequestDispatcher rd = request.getRequestDispatcher("/product/ShowProductInfo?productId=" + productId);
		rd.forward(request, response);
		return;
	}

}
