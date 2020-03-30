package _05_product.controller;

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

import _00_init.util.GlobalService;
import _05_product.model.ProductBean;
import _05_product.model.ProductFormatBean;
import _05_product.service.ProductService;
import _05_product.service.impl.ProductServiceImpl;

/* 未完成: 如果找不到Id，回商城首頁  */

@WebServlet("/product/ShowProductInfo")
public class ProductInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Set<String> contentSet1 = new HashSet<String>();
	Set<String> contentSet2 = new HashSet<String>();
	int memberId = 0;

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

		// 從jsp取得所點取的商品的productId為何
		String productIdStr = request.getParameter("productId");
		Integer productId = Integer.parseInt(productIdStr);

		Clob clob = null;
		String detail = "";
		ProductFormatBean firstProductFormat = null;

		// 利用getProduct取得該ID所擁有的資訊
		ProductService service = new ProductServiceImpl();
		ProductBean pb = service.getProduct(productId);

		if (pb != null) {
			// 使用iterator取出set裡的值
			Set<ProductFormatBean> formats = pb.getProductFormat();
			Iterator<ProductFormatBean> iterator = formats.iterator();
			if (iterator.hasNext()) {
				firstProductFormat = iterator.next();
			}
			// 取得第一項的titles
			String title1 = firstProductFormat.getFormatTitle1();
			String title2 = firstProductFormat.getFormatTitle2();
			// 利用set不重複性存入formatContents
			for (ProductFormatBean pfb : formats) {
				contentSet1.add(pfb.getFormatContent1());
				contentSet2.add(pfb.getFormatContent2());
			}

			// 取得Clob
			try {
				clob = pb.getDetail();
				if (clob != null) {
					detail = GlobalService.clobToString(clob);
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			// 把大家暫存到請求物件內
			request.setAttribute("product", pb);
			request.setAttribute("title1", title1);
			request.setAttribute("content1", contentSet1);
			request.setAttribute("title2", title2);
			request.setAttribute("content2", contentSet2);
			request.setAttribute("detail", detail);

			RequestDispatcher rd = request.getRequestDispatcher("/_05_product/productInfo.jsp");
			rd.forward(request, response);
			return;

		} else { // 如果找不到Id，回商城首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
	}
}
