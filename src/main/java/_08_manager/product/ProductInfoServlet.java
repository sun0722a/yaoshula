package _08_manager.product;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
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

import _00_init.util.GlobalService;
import _05_product.model.CategoryBean;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;
import _05_product.service.ProductService;

// 查詢商品詳細資料
@WebServlet("/manager/showProductInfo")
public class ProductInfoServlet extends HttpServlet {
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

		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			;
		}

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ProductService productService = ctx.getBean(ProductService.class);

		if (id != 0) {
			// 修改商品
			String detail = "";
			String title1 = "";
			String title2 = "";
			Set<String> contentSet1 = new HashSet<String>();
			Set<String> contentSet2 = new HashSet<String>();

			ProductBean pb = productService.getProduct(id);
			if (pb != null) {
				// 取得商品詳細資料(ProductFormatBean)
				Set<ProductFormatBean> formats = pb.getProductFormat();
				// 使用iterator取出第一筆規格資料
				Iterator<ProductFormatBean> iterator = formats.iterator();
				ProductFormatBean firstProductFormat = null;
				if (iterator.hasNext()) {
					firstProductFormat = iterator.next();
				}
				// 取得第一項的titles
				title1 = firstProductFormat.getFormatTitle1();
				title2 = firstProductFormat.getFormatTitle2();
				for (ProductFormatBean pfb : formats) {
					// 利用set不重複性存入商品規格
					contentSet1.add(pfb.getFormatContent1());
					contentSet2.add(pfb.getFormatContent2());
				}
				// 取得detail並轉成字串輸出
				try {
					Clob clob = pb.getDetail();
					if (clob != null) {
						detail = GlobalService.clobToString(clob);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

			request.setAttribute("product", pb);
			request.setAttribute("title1", title1);
			request.setAttribute("content1_set", contentSet1);
			request.setAttribute("title2", title2);
			request.setAttribute("content2_set", contentSet2);
			request.setAttribute("detail", detail);
		}
		Set<CategoryBean> angelProductCategorys = productService.getCategorys("天使");
		Set<CategoryBean> evilProductCategorys = productService.getCategorys("惡魔");

		request.setAttribute("angel_set", angelProductCategorys);
		request.setAttribute("evil_set", evilProductCategorys);
		request.setAttribute("productId", id);

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/product/productInfo.jsp");
		rd.forward(request, response);
		return;
	}

}
