package _06_article.controller;

import java.io.IOException;

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
import _06_article.model.ReportArticleBean;
import _06_article.model.ReportCommentBean;
import _06_article.service.ArticleService;

// 檢舉(文章、留言)
@WebServlet("/article/Report")
public class ReportServlet extends HttpServlet {
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

		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		String commentIdStr = request.getParameter("commentId");
		// 檢舉項目
		String reportItem = request.getParameter("reportItem");

		if (commentIdStr == "") {
			// 檢舉文章
			ArticleBean ab = (ArticleBean) session.getAttribute("article");
			ReportArticleBean bean = new ReportArticleBean(null, ab.getArticleId(), ab.getAuthorName(),
					ab.getPublishTime(), ab.getTitle(), mb.getMemberId(), reportItem);
			
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			ArticleService articleService = ctx.getBean(ArticleService.class);
			articleService.insertReportArticle(bean);
		} else {
			// 檢舉留言
			int commentId = Integer.parseInt(commentIdStr);
			
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			ArticleService articleService = ctx.getBean(ArticleService.class);
			CommentBean cb = articleService.getComment(commentId);
			
			ReportCommentBean bean = new ReportCommentBean(null, commentId, cb.getAuthorName(), cb.getPublishTime(),
					cb.getContent(), mb.getMemberId(), reportItem);
			articleService.insertReportComment(bean);
		}

		return;

	}

}
