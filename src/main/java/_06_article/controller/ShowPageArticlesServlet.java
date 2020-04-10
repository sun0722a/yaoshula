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

@WebServlet("/article/ShowPageArticles")
public class ShowPageArticlesServlet extends HttpServlet {
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

		// 讀取瀏覽器送來的搜尋條件
		String arrange = request.getParameter("arrange") == null ? "" : request.getParameter("arrange");
		String searchStr = request.getParameter("search") == null ? "" : request.getParameter("search");
		String categoryTitle = request.getParameter("categoryTitle") == null ? ""
				: request.getParameter("categoryTitle");
		String categoryName = request.getParameter("categoryName") == null ? "" : request.getParameter("categoryName");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService articleService = ctx.getBean(ArticleService.class);
		Map<Integer, ArticleBean> articleMap = articleService.getArticles(arrange, searchStr, categoryTitle,
				categoryName);

		request.setAttribute("searchStr", searchStr);
		request.setAttribute("arrange", arrange);
		request.setAttribute("categoryTitle", categoryTitle);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("articles_map", articleMap);

		RequestDispatcher rd = request.getRequestDispatcher("/_06_article/articlePage.jsp");
		rd.forward(request, response);
		return;

	}
}