package _06_article.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import _06_article.service.impl.ArticleServiceImpl;

@WebServlet("/article/AddComment")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
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
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		Integer authorId = mb.getId();
		String authorName = mb.getMemberId();

		// 呼叫ArticleDao的insertComment方法
//		ArticleService service = new ArticleServiceImpl();
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		ArticleService service = ctx.getBean(ArticleService.class);
		
		// 將所有文章資料封裝到CommentBean(類別的)物件
		CommentBean cb = new CommentBean(null, authorId, authorName, ts, content, ab, 0, "正常");
		// 如果有錯誤
		service.insertComment(cb);
		
		RequestDispatcher rd = request.getRequestDispatcher("/article/ShowArticleContent?articleId="+ab.getArticleId());
		rd.forward(request, response);
		return;

	}

}
