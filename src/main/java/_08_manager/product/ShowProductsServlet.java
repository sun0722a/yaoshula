package _08_manager.product;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _05_product.model.ProductBean;
import _05_product.service.ProductService;

// 查詢商品
@WebServlet("/manager/showProducts")
public class ShowProductsServlet extends HttpServlet {
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

		String searchStr = request.getParameter("searchStr") == null ? "" : request.getParameter("searchStr");
		String categoryTitle = request.getParameter("categoryTitle") == null ? "天使"
				: request.getParameter("categoryTitle");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ProductService productService = ctx.getBean(ProductService.class);
		Map<Integer, ProductBean> products = productService.getProducts(searchStr, categoryTitle);

		request.setAttribute("searchStr", searchStr);
		request.setAttribute("categoryTitle", categoryTitle);
		request.setAttribute("product_map", products);

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/product/allProducts.jsp");
		rd.forward(request, response);
		return;
	}

}
