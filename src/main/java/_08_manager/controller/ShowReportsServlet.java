package _08_manager.controller;

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
import _06_article.model.CommentBean;
import _06_article.service.ArticleService;

// 查詢被檢舉文章or留言
@WebServlet("/manager/showReports")
public class ShowReportsServlet extends HttpServlet {
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

		// 必須是空字串
		String searchStr = request.getParameter("searchStr");
		String cmd = request.getParameter("cmd");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService articleService = ctx.getBean(ArticleService.class);

		if (cmd.equals("article")) {
			Map<Integer, ArticleBean> articles = articleService.getReportArticles(searchStr);
			request.setAttribute("article_map", articles);

		} else if (cmd.equals("comment")) {
			Map<Integer, CommentBean> comments = articleService.getReportComments(searchStr);
			request.setAttribute("comment_map", comments);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/_08_manager/historyOrder.jsp");
		rd.forward(request, response);
		return;
	}

}
