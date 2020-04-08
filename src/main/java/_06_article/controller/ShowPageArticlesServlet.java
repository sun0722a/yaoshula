package _06_article.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _06_article.model.ArticleBean;
import _06_article.service.ArticleService;
import _06_article.service.impl.ArticleServiceImpl;

/* 無pageNo不能用cookie記之前瀏覽到哪些文章 */

@WebServlet("/article/ShowPageArticles")
public class ShowPageArticlesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	int pageNo = 1;
//	int memberId = 0; // 訪客Id=0

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
//		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
//		// 如果MemberBean物件不存在(未登入)，就memberId=0(訪客)
//		if (mb == null) {
//			memberId = 0;
//		} else {
//			// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
//			// 取出使用者的memberId，後面的Cookie會用到
//			memberId = mb.getId();
//		}

		// 讀取瀏覽器送來的 pageNo
//		String pageNoStr = request.getParameter("pageNo");
		String arrange = request.getParameter("arrange") == null ? "" : request.getParameter("arrange");
		String searchStr = request.getParameter("search") == null ? "" : request.getParameter("search");
		String categoryTitle = request.getParameter("categoryTitle") == null ? ""
				: request.getParameter("categoryTitle");
		String categoryName = request.getParameter("categoryName") == null ? "" : request.getParameter("categoryName");
		// 如果讀不到(之前沒點擊過商品區)
//		if (pageNoStr == null) {
//			pageNo = 1;
//			// 讀取瀏覽器送來的所有 Cookies
//			Cookie[] cookies = request.getCookies();
//			if (cookies != null) {
//				// 逐筆檢視Cookie內的資料
//				for (Cookie c : cookies) {
//					if (c.getName().equals(memberId + "pageNo")) {
//						try {
//							pageNo = Integer.parseInt(c.getValue().trim());
//						} catch (NumberFormatException e) {
//							;
//						}
//						break;
//					}
//				}
//			}
//		} else {
//			try {
//				pageNo = Integer.parseInt(pageNoStr.trim());
//			} catch (NumberFormatException e) {
//				pageNo = 1;
//			}
//		}
//		// 有輸入搜尋字串或有選排序
//		if (pageNo == -1) {
//			pageNo = 1;
//		}
		ArticleService service = new ArticleServiceImpl();
		Map<Integer, ArticleBean> articleMap = service.getArticles(arrange, searchStr, categoryTitle, categoryName);
		request.setAttribute("searchStr", searchStr);
		request.setAttribute("arrange", arrange);
		request.setAttribute("categoryTitle", categoryTitle);
		request.setAttribute("categoryName", categoryName);
//		request.setAttribute("pageNo", String.valueOf(pageNo));
//		request.setAttribute("totalPages", service.getTotalPages(searchStr, categoryTitle, categoryName));
		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		request.setAttribute("articles_map", articleMap);

		// 如果不是搜尋全部商品(沒有搜尋字串 and 沒有搜尋類別)=>不記錄到Cookie
		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
		// -----------------------
//		if (searchStr == "" && categoryTitle == "" && categoryName == "") {
//			Cookie pageNoCookie = new Cookie(memberId + "pageNo", String.valueOf(pageNo));
//			// 設定Cookie的存活期為30天
//			pageNoCookie.setMaxAge(30 * 24 * 60 * 60);
//			// 設定Cookie的路徑為 Context Path
//			pageNoCookie.setPath(request.getContextPath());
//			// 將Cookie加入回應物件內
//			response.addCookie(pageNoCookie);
//		}
		// -----------------------
		// 交由productList.jsp來顯示某頁的商品資料，同時準備『第一頁』、『前一頁』、『當前頁』、『下一頁』、『最末頁』等資料
		RequestDispatcher rd = request.getRequestDispatcher("/_06_article/articlePage.jsp");
		rd.forward(request, response);
		return;

	}
}