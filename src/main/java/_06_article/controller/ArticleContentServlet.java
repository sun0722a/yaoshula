package _06_article.controller;

import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
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
import _06_article.model.ArticleBean;
import _06_article.model.CommentBean;
import _06_article.service.ArticleService;

// 查詢文章內容
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

		// 從瀏覽器取得所選商品的articleId
		String articleIdStr = request.getParameter("articleId");
		Integer articleId = Integer.parseInt(articleIdStr);

		// 取得文章資料(ArticleBean)
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService service = ctx.getBean(ArticleService.class);
		ArticleBean ab = service.getArticle(articleId);

		String content = "";
		Clob clob = null;
		if (ab != null) {
			Set<CommentBean> comments = ab.getArticleComments();
			try {
				clob = ab.getContent();
				if (clob != null) {
					content = GlobalService.clobToString(clob);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			session.setAttribute("article", ab);
			request.setAttribute("comments_set", comments);
			request.setAttribute("content", content);

			RequestDispatcher rd = request.getRequestDispatcher("/_06_article/articleContent.jsp");
			rd.forward(request, response);
			return;

		} else {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
	}
}
