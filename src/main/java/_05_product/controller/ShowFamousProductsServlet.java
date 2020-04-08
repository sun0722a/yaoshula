package _05_product.controller;

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

@WebServlet("/product/ShowFamousProducts")
public class ShowFamousProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先取出session物件
		HttpSession session = request.getSession(false);

		// 使用逾時，回首頁
		if (session == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}

//		ProductService service = new ProductServiceImpl();
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ProductService service = ctx.getBean(ProductService.class);
		Map<Integer, ProductBean> angelProductMap = service.getFamousProducts("天使");
		Map<Integer, ProductBean> evilProductMap = service.getFamousProducts("惡魔");
		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		request.setAttribute("angel_products_map", angelProductMap);
		request.setAttribute("evil_products_map", evilProductMap);

		RequestDispatcher rd = request.getRequestDispatcher("/_05_product/productFamous.jsp");
		rd.forward(request, response);
		return;

	}
}