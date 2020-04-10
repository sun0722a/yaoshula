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

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _06_article.model.ArticleBean;
import _06_article.service.ArticleService;

@WebServlet("/article/ShowFamousArticles")
public class ShowFamousArticlesServlet extends HttpServlet {
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

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService articleService = ctx.getBean(ArticleService.class);
		Map<Integer, ArticleBean> angelArticleMap = articleService.getFamousArticles("天使");
		Map<Integer, ArticleBean> evilArticleMap = articleService.getFamousArticles("惡魔");

		request.setAttribute("angel_articles_map", angelArticleMap);
		request.setAttribute("evil_articles_map", evilArticleMap);

		RequestDispatcher rd = request.getRequestDispatcher("/_06_article/articleFamous.jsp");
		rd.forward(request, response);
		return;

	}
}