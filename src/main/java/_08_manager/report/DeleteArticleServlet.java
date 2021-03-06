package _08_manager.report;

import java.io.IOException;

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

/* 是否可直接使用insert方法? */

// 刪除被檢舉文章or留言
@WebServlet("/manager/deleteArticle")
public class DeleteArticleServlet extends HttpServlet {
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

		String cmd = request.getParameter("cmd");
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			;
		}
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService articleService = ctx.getBean(ArticleService.class);

		// 更新狀態
		if (cmd.equals("article")) {
			ArticleBean ab = articleService.getArticle(id);
			ab.setStatus("刪除");
			articleService.insertArticle(ab);
		} else if (cmd.equals("comment")) {
			CommentBean cb = articleService.getComment(id);
			cb.setStatus("刪除");
			articleService.insertComment(cb);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/manager/showReports");
		rd.forward(request, response);
		return;
		
	}
}
