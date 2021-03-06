package _06_article.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _01_register.model.MemberBean;
import _06_article.model.ArticleBean;
import _06_article.model.CommentBean;
import _06_article.service.ArticleService;

// 新增留言
@WebServlet("/article/AddComment")
public class AddCommentServlet extends HttpServlet {
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

		String content = request.getParameter("content");
		ArticleBean ab = (ArticleBean) session.getAttribute("article");
		// 如果經過loginFilter
		if (content == null) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/article/ShowArticleContent?articleId=" + ab.getArticleId());
			rd.forward(request, response);
			return;
		}

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		Integer authorId = mb.getId();
		String authorName = mb.getMemberId();

		// 將所有文章資料封裝到CommentBean(類別的)物件
		CommentBean cb = new CommentBean(null, authorId, authorName, ts, content, ab, "正常");

		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ArticleService articleService = ctx.getBean(ArticleService.class);
		articleService.insertComment(cb);

		// 需轉址才會直接更新留言
		response.sendRedirect(
				getServletContext().getContextPath() + "/article/ShowArticleContent?articleId=" + ab.getArticleId());
		return;

	}

}
