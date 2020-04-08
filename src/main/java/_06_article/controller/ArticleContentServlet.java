package _06_article.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _06_article.model.ArticleBean;
import _06_article.model.CommentBean;
import _06_article.service.ArticleService;
import _06_article.service.impl.ArticleServiceImpl;

@WebServlet("/article/ShowArticleContent")
public class ArticleContentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
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
		String articleIdStr = request.getParameter("articleId");
		Integer articleId = Integer.parseInt(articleIdStr);

		// 利用getArticle取得該ID所擁有的資訊
		ArticleService service = new ArticleServiceImpl();
		ArticleBean ab = service.getArticle(articleId);
		if (ab != null) {
		Set<CommentBean> comments = ab.getArticleComments();

		// 把大家暫存到請求物件內
		request.setAttribute("article", ab);
		request.setAttribute("comments_set", comments);

//			request.setAttribute("content1", contentSet1);
//			request.setAttribute("title2", title2);
//			request.setAttribute("content2", contentSet2);
//			request.setAttribute("detail", detail);
		// 設成Session，為了讓CarServlet抓到
//		request.setAttribute("articleId", articleId);

		RequestDispatcher rd = request.getRequestDispatcher("/_05_product/productInfo.jsp");
		rd.forward(request, response);
		return;

		} else { // 如果找不到Id，回商城首頁
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
	}
}
