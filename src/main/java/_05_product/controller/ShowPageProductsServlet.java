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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _01_register.model.MemberBean;
import _05_product.model.ProductBean;
import _05_product.service.ProductService;

/* 刪除 : request.setAttribute("baBean", service);--line78，不知道會不會出事 */

// 查詢指定商品(搜尋、排序)
@WebServlet("/product/ShowPageProducts")
public class ShowPageProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;
	int memberId = 0; // 訪客Id=0

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

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		// 如果未登入，就memberId=0(訪客Id)
		if (mb == null) {
			memberId = 0;
		} else {
			// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
			// 取出使用者的memberId，後面的Cookie會用到
			memberId = mb.getId();
		}

		// 讀取瀏覽器送來的搜尋條件
		String pageNoStr = request.getParameter("pageNo");
		String arrange = request.getParameter("arrange") == null ? "" : request.getParameter("arrange");
		String searchStr = request.getParameter("search") == null ? "" : request.getParameter("search");
		String categoryTitle = request.getParameter("categoryTitle") == null ? ""
				: request.getParameter("categoryTitle");
		String categoryName = request.getParameter("categoryName") == null ? "" : request.getParameter("categoryName");

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
		// 有輸入搜尋字串或有選排序
		if (pageNo == -1) {
			pageNo = 1;
		}

		// 取得本頁商品資料(Map<Integer, ProductBean>)
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ProductService service = ctx.getBean(ProductService.class);
		Map<Integer, ProductBean> productMap = service.getPageProducts(pageNo, arrange, searchStr, categoryTitle,
				categoryName);

		request.setAttribute("searchStr", searchStr);
		request.setAttribute("arrange", arrange);
		request.setAttribute("categoryTitle", categoryTitle);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("pageNo", String.valueOf(pageNo));
		request.setAttribute("totalPages", service.getTotalPages(searchStr, categoryTitle, categoryName));
		request.setAttribute("products_map", productMap);

		// 如果不是搜尋全部商品(沒有搜尋字串 and 沒有搜尋類別)=>不記錄到Cookie
		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
		// -----------------------
		if (searchStr == "" && categoryTitle == "" && categoryName == "") {
			Cookie pageNoCookie = new Cookie(memberId + "pageNo", String.valueOf(pageNo));
			// 設定Cookie的存活期為30天
			pageNoCookie.setMaxAge(30 * 24 * 60 * 60);
			// 設定Cookie的路徑為 Context Path
			pageNoCookie.setPath(request.getContextPath());
			// 將Cookie加入回應物件內
			response.addCookie(pageNoCookie);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/_05_product/productList.jsp");
		rd.forward(request, response);
		return;

	}
}