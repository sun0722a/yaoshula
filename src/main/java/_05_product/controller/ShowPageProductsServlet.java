package _05_product.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.MemberBean;
import _05_product.model.ProductBean;
import _05_product.service.ProductService;
import _05_product.service.impl.ProductServiceImpl;

/* 刪除 : request.setAttribute("baBean", service);--line78，不知道會不會出事 */

@WebServlet("/product/ShowPageProducts")
public class ShowPageProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先取出session物件
		HttpSession session = request.getSession(false);

		// 如果session物件不存在
		if (session == null) {
			// 請使用者登入
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/_02_login/login.jsp"));
			return;
		}
		// 登入成功後(經由LoginFilter)，Session範圍內才會有LoginOK對應的MemberBean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		// 取出使用者的memberId，後面的Cookie會用到
		int memberId = mb.getId();

		// 讀取瀏覽送來的 pageNo
		String pageNoStr = request.getParameter("pageNo");
		// 如果讀不到(之前沒點擊過商品區)
		if (pageNoStr == null) {
			pageNo = 1;
			// 讀取瀏覽器送來的所有 Cookies
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				// 逐筆檢視Cookie內的資料
				for (Cookie c : cookies) {
					if (c.getName().equals(memberId + "pageNo")) {
						try {
							pageNo = Integer.parseInt(c.getValue().trim());
						} catch (NumberFormatException e) {
							;
						}
						break;
					}
				}
			}
		} else {
			try {
				pageNo = Integer.parseInt(pageNoStr.trim());
			} catch (NumberFormatException e) {
				pageNo = 1;
			}
		}
		ProductService service = new ProductServiceImpl();
		Map<Integer, ProductBean> productMap = service.getPageProducts(pageNo);
		session.setAttribute("pageNo", String.valueOf(pageNo));
		request.setAttribute("totalPages", service.getTotalPages());
		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		session.setAttribute("products_map", productMap);

		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
		// -----------------------
		Cookie pageNoCookie = new Cookie(memberId + "pageNo", String.valueOf(pageNo));
		// 設定Cookie的存活期為30天
		pageNoCookie.setMaxAge(30 * 24 * 60 * 60);
		// 設定Cookie的路徑為 Context Path
		pageNoCookie.setPath(request.getContextPath());
		// 將Cookie加入回應物件內
		response.addCookie(pageNoCookie);
		// -----------------------
		// 交由productList.jsp來顯示某頁的商品資料，同時準備『第一頁』、『前一頁』、『當前頁』、『下一頁』、『最末頁』等資料
		RequestDispatcher rd = request.getRequestDispatcher("/_05_product/productList.jsp");
		rd.forward(request, response);
		return;

	}
}